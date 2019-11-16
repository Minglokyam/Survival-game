package com.example.survivalgame.dodgegame.presenter;

import com.example.survivalgame.User;
import com.example.survivalgame.dodgegame.model.DodgeGameManager;
import com.example.survivalgame.dodgegame.model.Plane;
import com.example.survivalgame.dodgegame.model.HP;
import com.example.survivalgame.dodgegame.model.Shell;
import com.example.survivalgame.dodgegame.view.View;

import java.time.Duration;

// This is the class which the game loop belongs to.
public class DodgeGamePresenter extends Thread {
  private DodgeGameManager dodgeGameManager;
  private Duration dodgeDuration;
  private boolean running = false;
  private View view;
  private User user;
  private float xTouchReference;
  private float yTouchReference;
  private Plane plane;
  private HP hp;

  public DodgeGamePresenter(View view, User user, int screenWidth, int screenHeight) {
    dodgeGameManager = new DodgeGameManager(screenWidth, screenHeight);
    plane = dodgeGameManager.getPlane();
    hp = dodgeGameManager.getHP();
    this.view = view;
    this.user = user;
    dodgeDuration = Duration.ofSeconds(30);
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  @Override
  public void run() {
    while (running) {
      long startTime = System.currentTimeMillis();
      view.clearCanvas();
      try {
        synchronized (this) {
          view.lockCanvas();
          dodgeGameManager.update();
          setPlane();
          checkHP();
          checkEndGame();
          user.setScore(user.getScore() + 1);

          view.drawColor(255, 255, 255);
          view.drawPath(plane.getXCoordinate(), plane.getYCoordinate());
          for (Shell shell : dodgeGameManager.getShells()) {
            view.drawOval(shell.getXCoordinate(), shell.getYCoordinate());
          }
          view.drawRectF(hp.getXCoordinate(), hp.getYCoordinate(), hp.getWidth(), hp.getLength());

          view.drawText("Life: " + user.getLife(), 0, 32);
          view.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64);
          view.drawText("Game time: " + dodgeDuration.getSeconds(), 0, 96);
          view.drawText("Score: " + user.getScore(), 0, 128);

          view.unlockCanvasAndPost();
        }
      } finally {
        long timeInterval = System.currentTimeMillis() - startTime;
        // update the total time
        user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
        // update the countdown
        dodgeDuration = dodgeDuration.minusMillis(timeInterval);
      }
    }
  }

  private void checkHP() {
    if (hp.getHPValue() <= 0) {
      user.setLife(
          user.getLife()
              - 1); // When hp( aka the life bar) goes to, or below, 0, life counter will minus 1.
      hp.setHPValue(100);
    }
  }

  private void checkEndGame() {
    if (dodgeDuration.getSeconds() <= 0 || user.getLife() == 0) {
      // After a success or a defeat, the player will go back to the main menu.
      running = false;
      view.toMain();
    }
  }

  public void setXTouchReference(float newXTouchReference) {
    xTouchReference = newXTouchReference;
  }

  public void setYTouchReference(float newYTouchReference) {
    yTouchReference = newYTouchReference;
  }

  private void setPlane() {
    plane.setXSpeed(((xTouchReference - plane.getXCoordinate()) / 6));
    float spdY = (yTouchReference - plane.getYCoordinate()) / 15;
    if (spdY > 20) {
      spdY = 20;
    } else if (spdY > 0 && spdY < 8) {
      spdY = 8;
    } else if (spdY < 0 && spdY > -8) {
      spdY = -8;
    } else if (spdY < -20) {
      spdY = -20;
    }
    plane.setYSpeed(spdY);
  }

  public void setMove() {
    plane.move();
  }

  public void setStop() {
    plane.stop();
  }
}
