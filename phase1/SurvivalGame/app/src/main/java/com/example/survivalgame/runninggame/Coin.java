package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Coin extends RunningGameItem {
  /** citation: https://www.youtube.com/watch?v=HzP9jJNmzSY */
  private int currentPosition = 0;

  private RectFactory rectFactory = new RectFactory();

  /** Build a coin. */
  Coin(RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate) {
    super(runningGameView, bmp, xCoordinate, yCoordinate);
  }

  /** update the coin's speed, and make the coin move */
  private void update() {
    setXCoordinate(getXCoordinate() - getRunningGameView().getMovingSpeed());

    // check the condition when the x coordinate is less than 0.
    if (getXCoordinate() < 0) {
      setXCoordinate(getRunningGameView().getWidth() + getBitmap().getWidth() / 4);
    }

    // move the position in the coin's bmp to next
    if (currentPosition >= 3) {
      currentPosition = 0;
    } else {
      currentPosition += 1;
    }
  }

  /** draw the coin. */
  @Override
  void draw(Canvas canvas) {
    // first update the coin's status.
    update();

    // then draw the coin by the rect.
    Rect a =
        rectFactory.createRect(
            currentPosition * getBitmap().getWidth() / 4,
            0,
            currentPosition * getBitmap().getWidth() / 4 + getBitmap().getWidth() / 4,
            42);
    Rect b =
        rectFactory.createRect(
            getXCoordinate(),
            getYCoordinate(),
            getXCoordinate() + getBitmap().getWidth() / 4,
            getYCoordinate() + 42);

    canvas.drawBitmap(getBitmap(), a, b, null);
  }

  /** check whether the runner touched the coin */
  boolean checkCollision(Rect runner, Rect coin) {
    return Rect.intersects(runner, coin);
  }

  /** get the rectangle of the coin. */
  Rect getRect() {
    return rectFactory.createRect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getBitmap().getWidth() / 4,
        getYCoordinate() + getBitmap().getHeight());
  }
}
