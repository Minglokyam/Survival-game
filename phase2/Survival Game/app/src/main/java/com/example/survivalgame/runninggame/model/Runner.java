package com.example.survivalgame.runninggame.model;

import android.graphics.Rect;

import java.util.List;

public class Runner extends RunningGameItem {
  private int screenHeight;

  // the running speed.
  private static int vSpeed = 1;

  private int groundHeight;

  private RectFactory rectFactory = new RectFactory();

  /** Build a runner. */
  Runner(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bmpSizeList,
      int groundHeight,
      int screenHeight) {
    super(xCoordinate, yCoordinate, movingSpeed, bmpSizeList);
    this.screenHeight = screenHeight;
    this.groundHeight = groundHeight;
  }

  /**
   * change the speed when hitting the ground and when jumping at highest point. citation:
   * https://www.youtube.com/watch?v=1WRNXLfT3F8
   */
  public void update() {
    if (getYCoordinate() < screenHeight - groundHeight - getBmpSizeList().get(1)) {
      // make the runner jump by adding vSpeed.
      vSpeed += 1;

      if (getYCoordinate() > screenHeight - groundHeight - getBmpSizeList().get(1) - vSpeed) {
        vSpeed = screenHeight - groundHeight - getBmpSizeList().get(1);
      }

    } else if (vSpeed > 0) {
      // set the vSpeed to 0 if it exceeds 0.
      vSpeed = 0;
      setYCoordinate(screenHeight - groundHeight - getBmpSizeList().get(1));
    }
    setYCoordinate(getYCoordinate() + vSpeed);
  }

  /** make the runner jump when touching the screen. */
  void onTouch() {
    if (getYCoordinate() >= screenHeight - groundHeight - getBmpSizeList().get(1)) {
      // set the vertical speed the runner will jump.
      vSpeed = -20;
    }
  }

  /** get the rectangle of the runner. */
  Rect getRect() {
    return rectFactory.createRect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getBmpSizeList().get(0),
        getYCoordinate() + getBmpSizeList().get(1));
  }
}
