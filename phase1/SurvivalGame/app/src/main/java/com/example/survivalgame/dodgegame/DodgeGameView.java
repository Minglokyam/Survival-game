package com.example.survivalgame.dodgegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.survivalgame.User;

import java.time.Duration;

public class DodgeGameView extends SurfaceView {
  private DodgeGameActivity dodgeGameActivity;
  private DodgeGameManager dodgeGameManager;
  private Duration dodgeDuration;
  private DodgeGameThread dodgeGameThread;
  private SurfaceHolder holder;
  private Paint paint;
  private User user;
  private Paint paintText;

  //Dependency Injection
  public DodgeGameView(Context context, User user, int screenWidth, int screenHeight) {
    super(context);
    dodgeGameActivity = (DodgeGameActivity) context;
    dodgeGameManager = new DodgeGameManager(screenWidth, screenHeight);
    this.user = user;
    dodgeDuration = Duration.ofSeconds(30);
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
    if (dodgeGameManager.getHP() <= 0) {
      user.setLife(user.getLife() - 1);
      dodgeGameManager.setHP(100);
    }
    if (dodgeDuration.getSeconds() <= 0 || user.getLife() == 0) {
      dodgeGameThread.setRunning(false);
      dodgeGameActivity.toMain();
    }
    user.setScore(user.getScore() + 1);
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    dodgeGameManager.draw(canvas);
    paintText.setColor(Color.BLACK);
    canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
    canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
    canvas.drawText("Game time: " + dodgeDuration.getSeconds(), 0, 96, paintText);
    canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_MOVE) {
      dodgeGameManager
          .getPlane()
          .setxSpeed((int) ((event.getX() - dodgeGameManager.getPlane().getXCoordinate()) / 6));
      int spdY = (int) ((event.getY() - dodgeGameManager.getPlane().getYCoordinate()) / 15);
      if (spdY > 20) {
        spdY = 20;
      } else if (spdY > 0 && spdY < 8) {
        spdY = 8;
      } else if (spdY < 0 && spdY > -8) {
        spdY = -8;
      } else if (spdY < -20) {
        spdY = -20;
      }
      dodgeGameManager.getPlane().setySpeed(spdY);
    }
    return true;
  }
}
