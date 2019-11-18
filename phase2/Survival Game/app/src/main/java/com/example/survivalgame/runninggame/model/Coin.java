package com.example.survivalgame.runninggame.model;

// We understand that importing Rect in model will violate MVP rule. The model should not have
// android class. We can solve it by creating our own Rect class. But this will be redundant and
// unnecessarily increase the number of classes.
import android.graphics.Rect;

import java.util.List;

class Coin extends RunningGameItem implements RandomItem {
  /** citation: https://www.youtube.com/watch?v=HzP9jJNmzSY */
  private int currentPosition = 0;

  private int screenWidth;

  private RectFactory rectFactory = new RectFactory();

  /** Build a coin. */
  Coin(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bmpSizeList,
      int screenWidth,
      int screenHeight,
      int groundHeight) {
    super(xCoordinate, yCoordinate, movingSpeed, bmpSizeList);
    this.screenWidth = screenWidth;
    int newYCoordinate = screenHeight - yCoordinate - groundHeight - getBmpSizeList().get(1);
    setYCoordinate(newYCoordinate);
  }

  @Override
  public int getCurrentPosition() {
    return currentPosition;
  }

  /** update the coin's speed, and make the coin move */
  @Override
  public void update() {
    setXCoordinate(getXCoordinate() - getMovingSpeed());

    // check the condition when the x coordinate is less than 0.
    if (getXCoordinate() < 0) {
      setXCoordinate(screenWidth + getBmpSizeList().get(0) / 4);
    }

    // move the position in the coin's bmp to next
    if (currentPosition >= 3) {
      currentPosition = 0;
    } else {
      currentPosition += 1;
    }
  }

  /** check whether the runner touched the coin */
  @Override
  public boolean checkCollision(Rect runner, Rect coin) {
    return Rect.intersects(runner, coin);
  }

  /** get the rectangle of the coin. */
  @Override
  public Rect getRect() {
    return rectFactory.createRect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getBmpSizeList().get(0) / 4,
        getYCoordinate() + getBmpSizeList().get(1));
  }

  @Override
  public boolean outOfScreen() {
    return getXCoordinate() < -80;
  }
}
