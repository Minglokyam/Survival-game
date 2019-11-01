package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Spike extends RunningGameItem {
  private RectFactory rectFactory = new RectFactory();

  /** build a spike. */
  Spike(RunningGameView runningGameView, Bitmap bmp, int xCoordinate, int yCoordinate) {
    super(runningGameView, bmp, xCoordinate, yCoordinate);
  }

  /** update the spike and make the spike move. */
  private void update() {
    // move the spikes with movingSpeed.
    setXCoordinate(getXCoordinate() - getRunningGameView().getMovingSpeed());
  }

  /** draw the spike. */
  public void draw(Canvas canvas) {
    // first update the spike.
    update();

    // then draw the coin by the rect.
    Rect a = rectFactory.createRect(0, 0, getBitmap().getWidth(), getBitmap().getHeight());

    Rect b =
        rectFactory.createRect(
            getXCoordinate(),
            getYCoordinate(),
            getXCoordinate() + getBitmap().getWidth(),
            getYCoordinate() + getBitmap().getHeight());
    canvas.drawBitmap(getBitmap(), a, b, null);
  }

  /** check whether the runner touched the spike. */
  boolean checkCollision(Rect runner, Rect spikes) {
    return Rect.intersects(runner, spikes);
  }

  /** get the rectangle of the spike. */
  Rect getRect() {
    return rectFactory.createRect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getBitmap().getWidth(),
        getYCoordinate() + getBitmap().getHeight());
  }
}
