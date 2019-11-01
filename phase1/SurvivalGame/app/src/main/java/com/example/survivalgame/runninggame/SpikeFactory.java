package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class SpikeFactory {
  Spike createSpike(RunningGameView runningGameView, Bitmap bmp, int x) {
    int y = runningGameView.getHeight() - Ground.height - bmp.getHeight();
    return new Spike(runningGameView, bmp, x, y);
  }
}
