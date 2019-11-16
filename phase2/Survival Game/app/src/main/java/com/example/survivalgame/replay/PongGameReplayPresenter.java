package com.example.survivalgame.replay;

import com.example.survivalgame.User;
import com.example.survivalgame.ponggame.model.PongGameItem;
import com.example.survivalgame.ponggame.view.PongView;

import java.time.Duration;
import java.util.List;

public class PongGameReplayPresenter extends Thread {
  private boolean running;
  private PongView pongView;
  private User user;
  /** the countdown of this game */
  private Duration pongDuration;

  public PongGameReplayPresenter(PongView pongView, User user, int screenWidth, int screenHeight) {
    this.user = user;
    this.pongView = pongView;
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
          pongView.drawColor(255, 255, 255);
          checkQuit();
          List<List<List<Float>>> replayList = user.getReplay();
          List<List<Float>> itemList = replayList.get(0);
          user.deleteReplay();
          for (List<Float> floatList : itemList) {
            if (floatList.get(0) == PongGameItem.CIRCLE) {
              pongView.drawCircle(floatList.get(1), floatList.get(2), floatList.get(3));
            } else {
              pongView.drawRect(
                  floatList.get(1), floatList.get(2), floatList.get(3), floatList.get(4));
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        pongView.unlockCanvasAndPost();
      }
    }
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  public void checkQuit() {
    if (user.isEmptyReplay()) { // If replay ends, return to main screen.
      running = false;
      pongView.toMain();
    }
  }

  public void setRunning(boolean newRunning) {
    running = newRunning;
  }
}
