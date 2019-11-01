package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class RunnerFactory {
  /**
   * the factory used to create the runner.
   */
  Runner createRunner(
          RunningGameView runningGameView,
          Bitmap bmp,
          int xCoordinate,
          int yCoordinate,
          int groundHeight) {
    return new Runner(runningGameView, bmp, xCoordinate, yCoordinate, groundHeight);
  }
}
