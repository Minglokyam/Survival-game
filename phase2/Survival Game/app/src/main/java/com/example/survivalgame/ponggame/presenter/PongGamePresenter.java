package com.example.survivalgame.ponggame.presenter;

import com.example.survivalgame.User;
import com.example.survivalgame.ponggame.view.PongView;
import com.example.survivalgame.ponggame.model.PongGameItem;
import com.example.survivalgame.ponggame.model.PongGameManager;

import java.time.Duration;
import java.util.List;

public class PongGamePresenter extends Thread {
  private boolean running;
  private PongView pongView;
  private User user;
  private PongGameManager pongGameManager;
  private long fps = 30;
  /** the countdown of this game */
  private Duration pongDuration;

  // Use dependency injection for pong game manager
  public PongGamePresenter(PongView pongView, PongGameManager pongGameManager, User user) {
    this.pongGameManager = pongGameManager;
    this.user = user;
    this.pongView = pongView;
    pongDuration = Duration.ofSeconds(30);
  }

  /**
   * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ and assignment
   * 1 fishtank
   */
  @Override
  public void run() {
    while (running) {
      long startTime = System.currentTimeMillis();
      pongView.clearCanvas();
      try {
        pongView.lockCanvas();
        synchronized (this) {
          pongGameManager.update(fps);
          setTouchReference();
          pongView.drawColor(255, 255, 255);
          pongView.drawText("Life: " + user.getLife(), 0, 32);
          pongView.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64);
          pongView.drawText("Game time: " + pongDuration.getSeconds(), 0, 96);
          pongView.drawText("Score: " + user.getScore(), 0, 128);
          checkQuit();
          List<List<Float>> itemList = pongGameManager.getItemList();
          for (List<Float> floatList : itemList) {
            if (floatList.get(0) == PongGameItem.CIRCLE) {
              pongView.drawCircle(floatList.get(1), floatList.get(2), floatList.get(3));
            } else {
              pongView.drawRect(
                  floatList.get(1), floatList.get(2), floatList.get(3), floatList.get(4));
            }
          }

          // save replay
          user.addReplay(itemList);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        pongView.unlockCanvasAndPost();
      }

      long timeInterval = System.currentTimeMillis() - startTime;
      // update the total time
      user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
      // update the countdown
      pongDuration = (pongDuration.minusMillis(timeInterval));

      if (timeInterval > 1) {
        fps = 1000 / timeInterval;
      }
    }
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  public void checkQuit() {
    user.setScore(user.getScore() + 1);
    if (user.getLife() == 0) { // If no life left, return to main screen.
      running = false;
      pongView.toScoreBoard();
    } else if (pongDuration.getSeconds() <= 0) { // If countdown reach 0, go to next game.
      running = false;
      pongView.toBeforeReplay();
    }
  }

  public void setRunning(boolean newRunning) {
    running = newRunning;
  }

  void setTouchReference() {
    float newTouchReference = pongGameManager.getTouchReference();
    pongView.setTouchReference(newTouchReference);
  }

  public void paddleMoveLeft() {
    pongGameManager.paddleMoveLeft();
  }

  public void paddleMoveRight() {
    pongGameManager.paddleMoveRight();
  }

  public void paddleStop() {
    pongGameManager.paddleStop();
  }
}
