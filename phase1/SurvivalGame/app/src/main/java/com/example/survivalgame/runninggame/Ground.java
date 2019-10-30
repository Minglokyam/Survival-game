package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Ground {

  // the first and second coordinate of the ground.
  private int x;
  private int y;

  // the height of the ground.
  public static int height;

  private Bitmap groundBmp;

  private RunningGameView view;

  /** build a ground. */
  public Ground(RunningGameView view, Bitmap bmp, int x, int y) {
    this.x = x;
    this.y = y;
    this.groundBmp = bmp;
    height = bmp.getHeight();
    this.view = view;
  }

  /** draw the ground. */
  public void draw(Canvas canvas) {
    canvas.drawBitmap(groundBmp, x, view.getHeight() - height, null);
  }
}
