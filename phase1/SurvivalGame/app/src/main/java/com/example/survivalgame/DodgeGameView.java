package com.example.survivalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.time.Duration;

public class DodgeGameView extends SurfaceView {
  private DodgeGameActivity dodgeGameActivity;
  private DodgeGameManager dodgeGameManager;
  private Duration dodgeDuration;
  private DodgeGameThread dodgeGameThread;
  private SurfaceHolder holder;
  public Paint paint;
  private User user;
  private Paint paintText;
  private int life;
  private int score;

  public DodgeGameView(Context context, User user, int screenWidth, int screenHeight) {
    super(context);
    dodgeGameActivity = (DodgeGameActivity) context;
    dodgeGameManager = new DodgeGameManager(screenWidth, screenHeight);
    this.user = user;
    life = user.getLife();
    score = user.getScore();
    dodgeDuration = Duration.ofSeconds(9);
    paintText = new Paint();
    paintText.setTextSize(40);
    dodgeGameThread = new DodgeGameThread(this, user);
    paint = new Paint();
    paint.setColor(Color.BLUE);
    holder = getHolder();
    holder.addCallback(
        new SurfaceHolder.Callback() {

          @Override
          public void surfaceCreated(SurfaceHolder holder) {
            dodgeGameThread.setRunning(true);
            dodgeGameThread.start();
          }

          @Override
          public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

          @Override
          public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            dodgeGameThread.setRunning(false);
            while (retry) {
              try {
                dodgeGameThread.join();
                retry = false;
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
        });
  }

  public Duration getDodgeDuration() {
    return dodgeDuration;
  }

  public void setDodgeDuration(Duration newDodgeDuration) {
    dodgeDuration = newDodgeDuration;
  }

  // This method updates all objects in the ArrayList!
  public void update() {
    dodgeGameManager.update();
    if (dodgeDuration.getSeconds() <= 0 || life == 0) {
      dodgeGameActivity.toMain();
    }
    score++;
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    dodgeGameManager.draw(canvas);
    paintText.setColor(Color.BLACK);
    canvas.drawText("Life: " + life, 0, 32, paintText);
    canvas.drawText("Total time: " + this.user.getTotalDuration().getSeconds(), 0, 64, paintText);
    canvas.drawText("Game time: " + dodgeDuration.getSeconds(), 0, 96, paintText);
    canvas.drawText("Score: " + score, 0, 128, paintText);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_MOVE) {
      dodgeGameManager.plane.setxSpeed((int) ((event.getX() - dodgeGameManager.plane.getX()) / 6));
      int spdY = (int) ((event.getY() - dodgeGameManager.plane.getY()) / 15);
      if (spdY > 20) {
        spdY = 20;
      } else if (spdY > 0 && spdY < 8) {
        spdY = 8;
      } else if (spdY < 0 && spdY > -8) {
        spdY = -8;
      } else if (spdY < -20) {
        spdY = -20;
      }
      dodgeGameManager.plane.setySpeed(spdY);
    }
    return true;
  }
}
