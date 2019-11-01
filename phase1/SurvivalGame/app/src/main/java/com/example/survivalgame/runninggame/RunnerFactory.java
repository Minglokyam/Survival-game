package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class RunnerFactory {
  Runner createRunner(
      RunningGameView runningGameView,
      Bitmap bmp,
      int xCoordinate,
      int yCoordinate,
      int groundHeight) {
    return new Runner(runningGameView, bmp, xCoordinate, yCoordinate, groundHeight);
  }
}
