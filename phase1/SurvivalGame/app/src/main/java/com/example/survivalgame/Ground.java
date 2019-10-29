package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Ground {

  // the first and second coordinate of the ground.
  private int x;
  private int y;

  // the height of the ground.
  static int height;

  private Bitmap groundBmp;

  private RunningGameView view;

    /**
     * build a ground.
     */
    Ground(RunningGameView view, Bitmap bmp, int x, int y) {
        this.x = x;
        this.y = y;
        this.groundBmp = bmp;
        Ground.height = bmp.getHeight();
        this.view = view;
    }

    /**
     * draw the ground.
     */
    void draw(Canvas canvas) {
    canvas.drawBitmap(groundBmp, x, view.getHeight() - height, null);
  }
}
