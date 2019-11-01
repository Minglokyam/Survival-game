package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;

import com.example.survivalgame.User;
// This is the class which the game loop belongs to.
class DodgeGameThread extends Thread {
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

  @Override
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
