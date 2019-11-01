package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Runner extends RunningGameItem {
  // the running speed.
  private static int vSpeed = 1;

  /** Build a runner. */
  public Runner(RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate) {
    super(runningGameView, bmp, xCoordinate, yCoordinate);
  }

  /**
   * change the speed when hitting the ground and when jumping at highest point. citation:
   * https://www.youtube.com/watch?v=1WRNXLfT3F8
   */
  private void update() {
    if (getYCoordinate()
        < getRunningGameView().getHeight() - Ground.height - getBitmap().getHeight()) {
      // make the runner jump by adding vSpeed.
      vSpeed += 1;

      if (getYCoordinate()
          > getRunningGameView().getHeight() - Ground.height - getBitmap().getHeight() - vSpeed) {
        vSpeed = getRunningGameView().getHeight() - Ground.height - getBitmap().getHeight();
      }

    } else if (vSpeed > 0) {
      // set the vSpeed to 0 if it exceeds 0.
      vSpeed = 0;
      setYCoordinate(getRunningGameView().getHeight() - Ground.height - getBitmap().getHeight());
    }
    setYCoordinate(getYCoordinate() + vSpeed);
  }

  /** draw the runner. */
  public void draw(Canvas canvas) {
    // first update the runner's position.
    update();

    // draw the runner using canvas.
    canvas.drawBitmap(getBitmap(), getXCoordinate(), getYCoordinate(), null);
  }

  /** make the runner jump when touching the screen. */
  public void onTouch() {
    if (getYCoordinate()
        >= getRunningGameView().getHeight() - Ground.height - getBitmap().getHeight()) {
      // set the vertical speed the runner will jump.
      vSpeed = -20;
    }
  }

  /** get the rectangle of the runner. */
  public Rect getRect() {
    return new Rect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getBitmap().getWidth(),
        getYCoordinate() + getBitmap().getHeight());
  }
}
