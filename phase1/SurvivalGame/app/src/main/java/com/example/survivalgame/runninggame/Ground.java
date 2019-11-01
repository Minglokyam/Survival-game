package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Ground extends RunningGameItem {
  // the height of the ground.
  public static int height;

  /** build a ground. */
  public Ground(RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate) {
    super(runningGameView, bmp, xCoordinate, yCoordinate);
    height = bmp.getHeight();
  }

  /** draw the ground. */
  public void draw(Canvas canvas) {
    canvas.drawBitmap(
        getBitmap(), getXCoordinate(), getRunningGameView().getHeight() - height, null);
  }
}
