package com.example.survivalgame;

import android.graphics.Canvas;

public class PongGameThread extends Thread {
  private boolean playing = true;
  private PongGameView pongGameView;
  private User user;

  public PongGameThread(PongGameView newPongGameView, User user) {
    this.user = user;
    this.pongGameView = newPongGameView;
  }

  @Override
  public void run() {
    while (playing) {
      long startTime = System.currentTimeMillis();
      if (!pongGameView.getStop()) {
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
      if (!pongGameView.getStop()) {
        user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
        pongGameView.setPongDuration(pongGameView.getPongDuration().minusMillis(timeInterval));
      }
      if (timeInterval > 1) {
        pongGameView.setFPS(1000 / timeInterval);
      }
    }
  }

  void setPlaying(boolean newPlaying) {
    playing = newPlaying;
  }
}
