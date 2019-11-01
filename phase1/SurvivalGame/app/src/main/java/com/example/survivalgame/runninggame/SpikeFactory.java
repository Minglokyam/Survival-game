package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class SpikeFactory {
  /**
   * the factory used to create the spike.
   */
  Spike createSpike(RunningGameView runningGameView, Bitmap bmp, int x, int groundHeight) {
    int y = runningGameView.getHeight() - groundHeight - bmp.getHeight();
    return new Spike(runningGameView, bmp, x, y);
  }
}
