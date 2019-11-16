package com.example.survivalgame.runninggame.model;

import java.util.List;

public class GroundFactory {
  /** the factory used to create the ground. */
  Ground createGround(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bmpSizeList,
      int screenHeight) {
    return new Ground(xCoordinate, yCoordinate, movingSpeed, bmpSizeList, screenHeight);
  }
}
