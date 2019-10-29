package com.example.survivalgame;

import android.graphics.Canvas;

public class DodgeGameThread extends Thread {
  private boolean running = false;
  private DodgeGameView dodgeGameView;
  private User user;

  DodgeGameThread(DodgeGameView view, User user) {
    this.dodgeGameView = view;
    this.user = user;
  }

  void setRunning(boolean running) {
    this.running = running;
  }

  public void run() {

    while (running) {
      long startTime = System.currentTimeMillis();
      Canvas canvas = null;
      try {
        canvas = dodgeGameView.getHolder().lockCanvas();
        synchronized (dodgeGameView.getHolder()) {
          dodgeGameView.update();
          dodgeGameView.draw(canvas);
        }
      } finally {
        long timeInterval = System.currentTimeMillis() - startTime;
        if (dodgeGameView.getDodgeDuration().minusMillis(timeInterval).getSeconds() >= 0) {
          dodgeGameView.setDodgeDuration(
              dodgeGameView.getDodgeDuration().minusMillis(timeInterval));
        }
        user.setTotalDuration((user.getTotalDuration().plusMillis(timeInterval)));

        if (canvas != null) {
          dodgeGameView.getHolder().unlockCanvasAndPost(canvas);
        }
      }
    }
  }
}
