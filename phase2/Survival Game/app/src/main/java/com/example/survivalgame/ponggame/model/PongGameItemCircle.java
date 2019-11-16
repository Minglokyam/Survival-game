package com.example.survivalgame.ponggame.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PongGameItemCircle extends PongGameItem {
  /** The radius of this rectangle */
  private float radius;

  PongGameItemCircle(
      PongGameManager pongGameManager, float radius, float xCoordinate, float yCoordinate) {
    super(pongGameManager, xCoordinate, yCoordinate);
    this.radius = radius;
    this.setShape(PongGameItem.CIRCLE);
  }

  /** A getter of radius */
  float getRadius() {
    return radius;
  }

  public List<Float> getFloatList() {
    List<Float> floatList = new ArrayList<>();
    floatList.add(getShape());
    floatList.add(getXCoordinate());
    floatList.add(getYCoordinate());
    floatList.add(radius);
    return floatList;
  }
}
