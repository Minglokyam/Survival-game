package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

import com.example.survivalgame.User;

public class PongGameThread extends Thread {
  private boolean playing = true;
  private PongGameView pongGameView;
  private User user;

  public PongGameThread(PongGameView newPongGameView, User user) {
    this.user = user;
    this.pongGameView = newPongGameView;
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  @Override
  public void run() {
    while (playing) {
      long startTime = System.currentTimeMillis();
      if (pongGameView.notStop()) {
        pongGameView.update();
      }

      Canvas canvas = null;
      try {
        canvas = pongGameView.getHolder().lockCanvas();
        if (canvas != null) {
          synchronized (pongGameView.getHolder()) {
            pongGameView.draw(canvas);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (canvas != null) {
          pongGameView.getHolder().unlockCanvasAndPost(canvas);
        }
      }

      long timeInterval = System.currentTimeMillis() - startTime;
      if (pongGameView.notStop()) {
        user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
        pongGameView.setPongDuration(pongGameView.getPongDuration().minusMillis(timeInterval));
      }
      if (timeInterval > 1) {
        pongGameView.setFPS(1000 / timeInterval);
      }
    }
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  public void setPlaying(boolean newPlaying) {
    playing = newPlaying;
  }
}
