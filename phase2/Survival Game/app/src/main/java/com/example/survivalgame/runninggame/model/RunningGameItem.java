package com.example.survivalgame.runninggame.model;

import java.util.List;

abstract class RunningGameItem {
  private int movingSpeed;
  /** The x-coordinate of this RunningGameItem */
  private int xCoordinate;
  /** The y-coordinate of this RunningGameItem */
  private int yCoordinate;

  private List<Integer> bmpSizeList;

  /** Create a RunningGameItem. */
  RunningGameItem(int xCoordinate, int yCoordinate, int movingSpeed, List<Integer> bmpSizeList) {
    this.movingSpeed = movingSpeed;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.bmpSizeList = bmpSizeList;
  }

  public List<Integer> getBmpSizeList() {
    return bmpSizeList;
  }

  public int getMovingSpeed() {
    return movingSpeed;
  }

  /** A getter of xCoordinate */
  public int getXCoordinate() {
    return xCoordinate;
  }

  /** A setter of xCoordinate */
  public void setXCoordinate(int newXCoordinate) {
    this.xCoordinate = newXCoordinate;
  }

  /** A getter of yCoordinate */
  public int getYCoordinate() {
    return yCoordinate;
  }

  /** A setter of yCoordinate */
  void setYCoordinate(int newYCoordinate) {
    this.yCoordinate = newYCoordinate;
  }
}
