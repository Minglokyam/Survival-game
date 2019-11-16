package com.example.survivalgame.ponggame.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PongGameItemRect extends PongGameItem {
  /** The width of this rectangle */
  private float width;
  /** The height of this rectangle */
  private float height;

  PongGameItemRect(
      PongGameManager pongGameManager,
      float width,
      float height,
      float xCoordinate,
      float yCoordinate) {
    super(pongGameManager, xCoordinate, yCoordinate);
    this.width = width;
    this.height = height;
    this.setShape(PongGameItem.RECTANGLE);
  }

  /** A getter of width */
  float getWidth() {
    return width;
  }

  /** A getter of height */
  float getHeight() {
    return height;
  }

  public List<Float> getFloatList() {
    List<Float> floatList = new ArrayList<>();
    floatList.add(getShape());
    floatList.add(getXCoordinate());
    floatList.add(getYCoordinate());
    floatList.add(width);
    floatList.add(height);
    return floatList;
  }
}
