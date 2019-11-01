package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

abstract class RunningGameItem {
  /** The x-coordinate of this RunningGameItem */
  private int xCoordinate;
  /** The y-coordinate of this RunningGameItem */
  private int yCoordinate;
  /** An instance of RunningGameView */
  private RunningGameView runningGameView;
  /** A bitmap */
  private Bitmap bmp;

  /** Create a RunningGameItem. */
  RunningGameItem(RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate) {
    this.runningGameView = runningGameView;
    this.bmp = bmp;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  /** A getter of runningGameView */
  RunningGameView getRunningGameView() {
    return runningGameView;
  }

  /** A getter of xCoordinate */
  int getXCoordinate() {
    return xCoordinate;
  }

  /** A setter of xCoordinate */
  void setXCoordinate(int newXCoordinate) {
    this.xCoordinate = newXCoordinate;
  }

  /** A getter of yCoordinate */
  int getYCoordinate() {
    return yCoordinate;
  }

  /** A setter of yCoordinate */
  void setYCoordinate(int newYCoordinate) {
    this.yCoordinate = newYCoordinate;
  }

  Bitmap getBitmap() {
    return bmp;
  }

  public abstract void draw(Canvas canvas);
}
