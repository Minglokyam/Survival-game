package com.example.survivalgame.runninggame;

import android.graphics.Canvas;

import com.example.survivalgame.User;

class RunningGameThread extends Thread {
  private RunningGameView view;
  private User user;

  // check whether the runner is running
  private boolean running;

  RunningGameThread(RunningGameView view, User user) {
    this.view = view;
    this.user = user;
  }

  void setRunning(boolean newRunning) {
    running = newRunning;
  }

  @Override
  public void run() {
    long sleepTime;
    while (running) {
      long startTime = System.currentTimeMillis();
      Canvas canvas = null;
      try {
        canvas = view.getHolder().lockCanvas();
        synchronized (view.getHolder()) {
          view.update();
          view.draw(canvas);
        }
      } finally {
        if (canvas != null) {
          view.getHolder().unlockCanvasAndPost(canvas);
        }
      }
      sleepTime = view.getFps() - (System.currentTimeMillis() - startTime);
      try {
        if (sleepTime > 0) {
          sleep(sleepTime);
        } else {
          sleep(10);
        }
      } catch (Exception e) {
      }
      long timeInterval = System.currentTimeMillis() - startTime;
      user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
      view.setRunningDuration(view.getRunningDuration().minusMillis(timeInterval));
      if (timeInterval > 1) {
        view.setFps(1000 / timeInterval);
      }
    }
  }
}
