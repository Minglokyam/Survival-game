package com.example.survivalgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.time.Duration;

public class PongGameView extends SurfaceView {
  /** The screen width */
  int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

  /** The screen height */
  int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** A Pong Game Thread */
  PongGameThread thread;

  private boolean stop = true;

  private long FPS;

  private PongGameManager pongGameManager;

  private Paint paintText;

  private Duration pongDuration;

  public Duration getPongDuration() {
    return pongDuration;
  }

  private PongGameActivity pongGameActivity;

  private User user;

  public void setPongDuration(Duration newPongDuration) {
    pongDuration = newPongDuration;
  }

  public PongGameView(Context context, User user) {
    super(context);
    pongGameActivity = (PongGameActivity) context;
    this.user = user;
    pongGameManager = new PongGameManager(screenWidth, screenHeight, user);
    setFocusable(true);
    paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    // =======================================
    pongDuration = Duration.ofSeconds(15);
  }

  public void update() {
    pongGameManager.update(FPS);
    user.setScore(user.getScore() + 1);
    if (user.getLife() == 0) {
      stop = true;
      thread.setPlaying(false);
      pongGameActivity.toMain();
    } else if (pongDuration.getSeconds() <= 0) {
      stop = true;
      thread.setPlaying(false);
      pongGameActivity.toDodge();
    }
  }

  public void draw(Canvas canvas) {
    super.draw(canvas);
    canvas.drawColor(Color.rgb(255, 255, 255));
    if (!stop) {
      canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
      canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
      canvas.drawText("Game time: " + pongDuration.getSeconds(), 0, 96, paintText);
      canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);
    }

    pongGameManager.draw(canvas);
  }

  @Override
  public boolean onTouchEvent(MotionEvent motionEvent) {
    RectPaddle rectPaddle = pongGameManager.getRectPaddle();
    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN:
        stop = false;
        if (motionEvent.getX() > rectPaddle.getXCoordinate() + rectPaddle.getWidth() / 2) {
          pongGameManager.paddleMoveRight();
        } else {
          pongGameManager.paddleMoveLeft();
        }
        break;
      case MotionEvent.ACTION_UP:
        pongGameManager.paddleStop();
        break;
    }
    return true;
  }

  public void pause() {
    try {
      thread.setPlaying(false);
      thread.join();
    } catch (InterruptedException e) {
      Log.e("Error:", "joining thread");
    }
  }

  public void resume() {
    thread = new PongGameThread(this, user);
    thread.start();
  }

  public void setFPS(long newFPS) {
    FPS = newFPS;
  }

  public boolean getStop() {
    return stop;
  }
}
