package com.example.survivalgame.ponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.survivalgame.User;

import java.time.Duration;

public class PongGameView extends SurfaceView {
  /** The Activity of this game */
  private PongGameActivity pongGameActivity;
  /** The Thread of this game */
  private PongGameThread pongGameThread;
  /** Pong Game Manager is responsible for storing the objects of this game and their motions. */
  private PongGameManager pongGameManager;
  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  /** The duration for one update */
  private long FPS = 30;

  private Paint paintText;

  private Duration pongDuration;

  private User user;

  public PongGameView(Context context, User user) {
    super(context);
    pongGameActivity = (PongGameActivity) context;
    pongGameManager = new PongGameManager(user);

    this.user = user;

    setFocusable(true);
    paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);

    pongGameThread = new PongGameThread(this, user);
    SurfaceHolder surfaceHolder = getHolder();
    surfaceHolder.addCallback(
        new SurfaceHolder.Callback() {
          @Override
          public void surfaceCreated(SurfaceHolder holder) {
            pongGameThread.setRunning(true);
            pongGameThread.start();
          }

          @Override
          public void surfaceChanged(SurfaceHolder holder, int a, int b, int c) {}

          @Override
          public void surfaceDestroyed(SurfaceHolder holder) {}
        });
    // =======================================
    pongDuration = Duration.ofSeconds(30);
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  public void update() {
    pongGameManager.update(FPS);
    user.setScore(user.getScore() + 1);
    if (user.getLife() == 0) {
      pongGameThread.setRunning(false);
      pongGameActivity.toMain();
    } else if (pongDuration.getSeconds() <= 0) {
      pongGameThread.setRunning(false);
      pongGameActivity.toDodge();
    }
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    canvas.drawColor(Color.rgb(255, 255, 255));
    canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
    canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
    canvas.drawText("Game time: " + pongDuration.getSeconds(), 0, 96, paintText);
    canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);
    pongGameManager.draw(canvas);
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  @Override
  public boolean onTouchEvent(MotionEvent motionEvent) {
    RectPaddle rectPaddle = pongGameManager.getRectPaddle();
    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN:
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

  public void setFPS(long newFPS) {
    FPS = newFPS;
  }

  public Duration getPongDuration() {
    return pongDuration;
  }

  public void setPongDuration(Duration newPongDuration) {
    pongDuration = newPongDuration;
  }
}
