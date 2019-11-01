package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class GroundFactory {
  Ground createGround(
      RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate) {
    int height = bmp.getHeight();
    return new Ground(runningGameView, bmp, xCoordinate, yCoordinate, height);
  }
}
