package com.example.survivalgame.runninggame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.content.Context;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.view.SurfaceHolder.Callback;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.survivalgame.R;
import com.example.survivalgame.User;

import java.time.Duration;

public class RunningGameView extends SurfaceView {
  private RunningGameThread runningGameThread;
  private SurfaceHolder holder;

  // the moving speed of the objects in the game.
  private int movingSpeed = 10;

  // the image holder of the runner.
  private Bitmap runnerBMP;
  // the image holder of the coin.
  private Bitmap coinBMP;
  // the image holder of the ground.
  private Bitmap groundBMP;
  // the image holder of the spike.
  private Bitmap spikeBMP;

  // the duration time of the running game.
  private Duration runningDuration;

  // the fps of the running game.
  private long fps;

  private RunningGameActivity runningGameActivity;

  private User user;

  private RunningGameManager runningGameManager;

  private Paint paintText;

  /** set up the GameView. */
  public RunningGameView(Context context, User user) {
    super(context);
    paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    this.user = user;
    runningGameActivity = (RunningGameActivity) context;
    runningGameThread = new RunningGameThread(this, user);
    holder = getHolder();
    holder.addCallback(
        new Callback() {

          @Override
          public void surfaceDestroyed(SurfaceHolder holder0) {}

          @Override
          public void surfaceCreated(SurfaceHolder holder0) {
            runningGameThread.setRunning(true);
            runningGameThread.start();
          }

          @Override
          public void surfaceChanged(SurfaceHolder holder0, int a, int b, int c) {}
        });

    // get the image of the objects.
    runnerBMP = BitmapFactory.decodeResource(getResources(), R.drawable.runner);
    coinBMP = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    groundBMP = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
    spikeBMP = BitmapFactory.decodeResource(getResources(), R.drawable.spikes);

    runningGameManager = new RunningGameManager(this);
    // =======================================
    runningDuration = Duration.ofSeconds(30);
  }

  RunningGameThread getRunningGameThread() {
    return runningGameThread;
  }

  int getMovingSpeed() {
    return movingSpeed;
  }

  Bitmap getRunnerBMP() {
    return runnerBMP;
  }

  Bitmap getCoinBMP() {
    return coinBMP;
  }

  Bitmap getGroundBMP() {
    return groundBMP;
  }

  Bitmap getSpikeBMP() {
    return spikeBMP;
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
  void update() {
    user.setScore(user.getScore() + 1);
    runningGameManager.update(runningGameActivity, user);
    // when the game time runs out, jump to next game.
    if (runningDuration.getSeconds() <= 0) {
      runningGameActivity.toPong();
    }
  }

  /** Draw all the objects on the screen. */
  public void draw(Canvas canvas) {
    super.draw(canvas);
    // draw the life, total time, game time and score.
    canvas.drawColor(Color.WHITE);
    canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
    canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
    canvas.drawText("Game time: " + runningDuration.getSeconds(), 0, 96, paintText);
    canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);
    runningGameManager.draw(canvas);
  }
}
