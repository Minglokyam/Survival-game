package com.example.survivalgame.replay.presenter;

import com.example.survivalgame.general.User;
import com.example.survivalgame.replay.view.ReplayView;
import com.example.survivalgame.replay.model.PongGameItem;

import java.util.List;

public class PongGameReplayPresenter extends Thread {
  private boolean running;
  private ReplayView replayView;
  private User user;

  public PongGameReplayPresenter(ReplayView replayView, User user) {
    this.user = user;
    this.replayView = replayView;
  }

  /**
   * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ and assignment
   * 1 fishtank
   */
  @Override
  public void run() {
    while (running) {
      replayView.clearCanvas();
      try {
        replayView.lockCanvas();
        synchronized (this) {
          replayView.drawColor(255, 255, 255);
          checkQuit();
          List<List<List<Float>>> replayList = user.getReplay();
          List<List<Float>> itemList = replayList.get(0);
          user.deleteReplay();
          for (List<Float> floatList : itemList) {
            if (floatList.get(0) == PongGameItem.CIRCLE) {
              replayView.drawCircle(floatList.get(1), floatList.get(2), floatList.get(3));
            } else {
              replayView.drawRect(
                  floatList.get(1), floatList.get(2), floatList.get(3), floatList.get(4));
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        replayView.unlockCanvasAndPost();
      }
    }
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  public void checkQuit() {
    if (user.isEmptyReplay()) { // If replay ends, return to main screen.
      running = false;
      replayView.toDodge();
    }
  }

  public void setRunning(boolean newRunning) {
    running = newRunning;
  }
}
