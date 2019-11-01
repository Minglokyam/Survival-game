package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

import com.example.survivalgame.User;

class PongGameThread extends Thread {
  private boolean running = true;
  private PongGameView pongGameView;
  private User user;

  public PongGameThread(PongGameView newPongGameView, User user) {
    this.user = user;
    this.pongGameView = newPongGameView;
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  @Override
  public void run() {
    while (running) {
      long startTime = System.currentTimeMillis();

      Canvas canvas = null;
      try {
        canvas = pongGameView.getHolder().lockCanvas();
        if (canvas != null) {
          synchronized (pongGameView.getHolder()) {
            pongGameView.update();
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
      user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
      pongGameView.setPongDuration(pongGameView.getPongDuration().minusMillis(timeInterval));

      if (timeInterval > 1) {
        pongGameView.setFPS(1000 / timeInterval);
      }
    }
  }

  /** citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ */
  public void setRunning(boolean newRunning) {
    running = newRunning;
  }
}
