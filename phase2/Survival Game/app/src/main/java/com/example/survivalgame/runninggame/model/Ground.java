package com.example.survivalgame.runninggame.model;

import java.util.List;

public class Ground extends RunningGameItem {
  private int newYCoordinate;

  /** build a ground. */
  Ground(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bmpSizeList,
      int screenHeight) {
    super(xCoordinate, yCoordinate, movingSpeed, bmpSizeList);
    newYCoordinate = screenHeight - getBmpSizeList().get(1) - 200;
  }

  public int getNewYCoordinate() {
    return newYCoordinate;
  }
}
