package com.example.survivalgame.runninggame.model;

import com.example.survivalgame.runninggame.model.Runner;

import java.util.List;

class RunnerFactory {
  /** the factory used to create the runner. */
  Runner createRunner(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bmpSizeList,
      int groundHeight,
      int screenHeight) {
    return new Runner(
        xCoordinate, yCoordinate, movingSpeed, bmpSizeList, groundHeight, screenHeight);
  }
}
