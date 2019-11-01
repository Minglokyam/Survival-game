package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Ground extends RunningGameItem {
  // the height of the ground.
  private int height;

  /** build a ground. */
  Ground(
      RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate, int height) {
    super(runningGameView, bmp, xCoordinate, yCoordinate);
    this.height = height;
  }

  int getHeight() {
    return height;
  }

  /** draw the ground. */
  public void draw(Canvas canvas) {
    canvas.drawBitmap(
        getBitmap(), getXCoordinate(), getRunningGameView().getHeight() - height, null);
  }
}
