package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Spikes {

  private int x, y;
  private Bitmap bmp;
  private RunningGameView view;

  Spikes(RunningGameView view, Bitmap bmp, int x) {
    this.view = view;
    this.bmp = bmp;
    this.x = x;
    this.y = view.getHeight() - Ground.height - bmp.getHeight();
  }

  /** update the spike and make the spike move. */
  private void update() {
    // move the spikes with movingSpeed.
    x -= RunningGameView.movingSpeed;
  }

  /** draw the spike. */
  void draw(Canvas canvas) {
    // first update the spike.
    update();

    // then draw the coin by the rect.
    Rect a = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
    Rect b = new Rect(x, y, x + bmp.getWidth(), y + bmp.getHeight());
    canvas.drawBitmap(bmp, a, b, null);
  }

  /** check whether the runner touched the spike. */
  boolean checkCollision(Rect runner, Rect spikes) {
    return Rect.intersects(runner, spikes);
  }

  /** get the rectangle of the spike. */
  Rect getRect() {
    return new Rect(this.x, this.y, this.x + bmp.getWidth(), this.y + bmp.getHeight());
  }

  /** the getter of the first coordinate of the spike. */
  int getX() {
    return x;
  }
}
