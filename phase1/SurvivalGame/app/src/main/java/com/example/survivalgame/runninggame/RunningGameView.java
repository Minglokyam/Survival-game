package com.example.survivalgame.runninggame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.content.Context;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.view.SurfaceHolder.Callback;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Paint;

import com.example.survivalgame.R;
import com.example.survivalgame.User;

import java.time.Duration;
import java.util.Iterator;

public class RunningGameView extends SurfaceView {
  public RunningGameThread thread;
  private SurfaceHolder holder;

  // the moving speed of the objects in the game.
  public static int movingSpeed = 10;

  // the image holder of the runner.
  public Bitmap runnerBmp;
  // the image holder of the coin.
  public Bitmap coinBmp;
  // the image holder of the ground.
  public Bitmap groundBmp;
  // the image holder of the spike.
  public Bitmap spikesBmp;

  // the duration time of the running game.
  private Duration runningDuration;

  // the fps of the running game.
  private long fps;

  private RunningGameActivity runningGameActivity;

  private User user;

  private RunningGameManager runningGameManager;

  private Paint paintText = new Paint();

  /** set up the GameView. */
  public RunningGameView(Context context, User user) {
    super(context);
    this.user = user;
    runningDuration = Duration.ofSeconds(50);
    runningGameActivity = (RunningGameActivity) context;
    thread = new RunningGameThread(this, user);
    holder = getHolder();
    holder.addCallback(
        new Callback() {

          public void surfaceDestroyed(SurfaceHolder holder0) {}

          public void surfaceCreated(SurfaceHolder holder0) {
            thread.setRunning();
            thread.start();
          }

          public void surfaceChanged(SurfaceHolder holder0, int a, int b, int c) {}
        });

    // get the image of the objects.
    runnerBmp = BitmapFactory.decodeResource(getResources(), R.drawable.runner);
    coinBmp = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    groundBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
    spikesBmp = BitmapFactory.decodeResource(getResources(), R.drawable.spikes);

    runningGameManager = new RunningGameManager(this);
  }

  /** getter of the runningDuration */
  public Duration getRunningDuration() {
    return runningDuration;
  }

  /** setter of the runningDuration. */
  public void setRunningDuration(Duration newRunningDuration) {
    runningDuration = newRunningDuration;
  }

  /** setter of the fps in the running game. */
  public void setFps(long n) {
    fps = n;
  }

  /** getter of the fps in the running game. */
  public long getFps() {
    return fps;
  }

  /** make the runner jump and restart the game once touching on the screen. */
  public boolean onTouchEvent(MotionEvent event) {
    runningGameManager.runner.onTouch();
    return false;
  }

  /** update the objects and current game status. */
  private void update() {
    user.setScore(user.getScore() + 1);
    runningGameManager.update();
    updateCoin();
    updateSpike();

    // when the game time runs out, jump to next game.
    if (runningDuration.getSeconds() <= 0) {
      runningGameActivity.toPong();
    }
  }

  /** update the coin when it moves out of the screen or the runner touches it. */
  private void updateCoin() {
    boolean collide = false;
    Iterator<Coin> coinIterator = runningGameManager.coins.iterator();
    while (coinIterator.hasNext() && !collide) {
      Coin coin = coinIterator.next();
      Rect runnerRect = runningGameManager.runner.getRect();
      Rect coinRect = coin.getRect();
      if (coin.checkCollision(runnerRect, coinRect)) {
        // remove the coin once the runner touch this coin.
        coinIterator.remove();
        // add points to the score when the runner touches a coin.
        user.setScore(user.getScore() + 100);
        collide = true;
      }
    }
  }

  /** update the spike when it moves out of the screen or the runner touches it. */
  private void updateSpike() {
    boolean collide = false;
    Iterator<Spike> spikeIterator = runningGameManager.spikes.iterator();
    while (spikeIterator.hasNext() && !collide) {
      Spike spike = spikeIterator.next();
      Rect runnerRect = runningGameManager.runner.getRect();
      Rect spikeRect = spike.getRect();
      if (spike.checkCollision(runnerRect, spikeRect)) {
        user.setLife(user.getLife() - 1);
        spikeIterator.remove();
      }
      if (user.getLife() == 0) {
        runningGameActivity.toMain();
        collide = true;
      }
    }
  }

  /** Draw all the objects on the screen. */
  public void draw(Canvas canvas) {
    super.draw(canvas);

    update();

    // draw the life, total time, game time and score.
    canvas.drawColor(Color.WHITE);
    paintText.setTextSize(40);
    canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
    canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
    canvas.drawText("Game time: " + runningDuration.getSeconds(), 0, 96, paintText);
    canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);

    // draw the runner.
    runningGameManager.runner.draw(canvas);

    // draw the coin
    for (int i = 0; i < runningGameManager.coins.size(); i++) {
      runningGameManager.coins.get(i).draw(canvas);
    }

    // draw the spikes
    for (int i = 0; i < runningGameManager.spikes.size(); i++) {
      runningGameManager.spikes.get(i).draw(canvas);
    }

    // draw the ground.
    runningGameManager.ground.draw(canvas);
  }
}
