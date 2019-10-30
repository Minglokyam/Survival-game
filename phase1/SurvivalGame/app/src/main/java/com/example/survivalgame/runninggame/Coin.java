package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Coin {
  /** citation: https://www.youtube.com/watch?v=HzP9jJNmzSY */

  // the first and second coordinate of the coin.
  private int x;

  private int y;

  private Bitmap bmp;

  private RunningGameView view;

  private int currentPosition = 0;

  /** Build a coin. */
  public Coin(RunningGameView view, Bitmap bmp, int x, int y) {
    this.x = x;
    this.y = view.getHeight() - y - Ground.height - bmp.getHeight();

    this.view = view;
    this.bmp = bmp;
  }

  /** update the coin's speed, and make the coin move */
  private void update() {
    x -= RunningGameView.movingSpeed;

    // check the condition when the x coordinate is less than 0.
    if (x < 0) {
      x = view.getWidth() + bmp.getWidth() / 4;
    }

    // move the position in the coin's bmp to next
    if (currentPosition >= 3) {
      currentPosition = 0;
    } else {
      currentPosition += 1;
    }
  }

  /** draw the coin. */
  public void draw(Canvas canvas) {
    // first update the coin's status.
    update();

    // then draw the coin by the rect.
    Rect a =
        new Rect(
            currentPosition * bmp.getWidth() / 4,
            0,
            currentPosition * bmp.getWidth() / 4 + bmp.getWidth() / 4,
            42);
    Rect b = new Rect(x, y, x + bmp.getWidth() / 4, y + 42);
    canvas.drawBitmap(bmp, a, b, null);
  }

  /** check whether the runner touched the coin */
  public boolean checkCollision(Rect runner, Rect coin) {
    return Rect.intersects(runner, coin);
  }

  /** get the rectangle of the coin. */
  public Rect getRect() {
    return new Rect(x, y, x + bmp.getWidth() / 4, y + bmp.getHeight());
  }

  /** the getter of first coordinate of coin. */
  public int getX() {
    return x;
  }
}
