package com.example.survivalgame.runninggame.model;

// We understand that importing Rect in model will violate MVP rule. The model should not have
// android class. We can solve it by creating our own Rect class. But this will be redundant and
// unnecessarily increase the number of classes.
import android.graphics.Rect;

import java.util.List;

public class Spike extends RunningGameItem implements RandomItem {
  private RectFactory rectFactory = new RectFactory();

  /** build a spike. */
  public Spike(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bnpSizeList,
      int screenHeight,
      int groundHeight) {
    super(xCoordinate, yCoordinate, movingSpeed, bnpSizeList);
    int newYCoordinate = screenHeight - groundHeight - getBmpSizeList().get(1);
    setYCoordinate(newYCoordinate);
  }

  /** update the spike and make the spike move. */
  @Override
  public void update() {
    // move the spikes with movingSpeed.
    setXCoordinate(getXCoordinate() - getMovingSpeed());
  }

  /** check whether the runner touched the spike. */
  @Override
  public boolean checkCollision(Rect runner, Rect spikes) {
    return Rect.intersects(runner, spikes);
  }

  /** get the rectangle of the spike. */
  @Override
  public Rect getRect() {
    return rectFactory.createRect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getBmpSizeList().get(0),
        getYCoordinate() + getBmpSizeList().get(1));
  }

  @Override
  public boolean outOfScreen() {
    return getXCoordinate() < -80;
  }

  @Override
  public int getCurrentPosition() {
    return 0;
  }
}
