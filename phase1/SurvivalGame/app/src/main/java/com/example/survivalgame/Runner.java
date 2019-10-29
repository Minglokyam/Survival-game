package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Runner {
  // the first and second coordinate of the runner.
  private static int x;
  private static int y;

  // the running speed.
  private static int vSpeed = 1;

  // the bmp picture of the runner.
  private Bitmap bmp;

  private RunningGameView view;

  /** Build a runner. */
  Runner(RunningGameView view, Bitmap bmp, int x, int y) {
    this.view = view;
    Runner.x = x;
    Runner.y = y;
    this.bmp = bmp;
  }

  /** change the speed when hitting the ground and when jumping at highest point. */
  private void update() {
    if (y < view.getHeight() - Ground.height - bmp.getHeight()) {
      // make the runner jump by adding vSpeed.
      vSpeed += 1;

      if (y > view.getHeight() - Ground.height - bmp.getHeight() - vSpeed) {
        vSpeed = view.getHeight() - Ground.height - bmp.getHeight();
      }

    } else if (vSpeed > 0) {
      // set the vSpeed to 0 if it exceeds 0.
      vSpeed = 0;
      y = view.getHeight() - Ground.height - bmp.getHeight();
    }

    y += vSpeed;
  }

  /** draw the runner. */
  void draw(Canvas canvas) {
    // first update the runner's position.
    update();

    // draw the runner using canvas.
    canvas.drawBitmap(bmp, x, y, null);
  }

  /** make the runner jump when touching the screen. */
  void onTouch() {
    if (y >= view.getHeight() - Ground.height - bmp.getHeight()) {
      // set the vertical speed the runner will jump.
      vSpeed = -20;
    }
  }

  /** get the rectangle of the runner. */
  Rect getRect() {
    return new Rect(x, y, x + bmp.getWidth(), y + bmp.getHeight());
  }
}
