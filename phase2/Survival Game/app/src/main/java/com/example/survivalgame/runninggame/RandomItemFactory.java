package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class RandomItemFactory {
  final int COIN = 0;
  final int SPIKE = 1;

  /** the factory used to create a running game random item. */
  RandomItem createRandomItem(
      RunningGameView runningGameView,
      Bitmap bmp,
      int xCoordinate,
      int yCoordinate,
      int groundHeight,
      int itemType) {
    if (itemType == COIN) {
      int newYCoordinate =
          runningGameView.getHeight() - yCoordinate - groundHeight - bmp.getHeight();
      return new Coin(runningGameView, bmp, xCoordinate, newYCoordinate);
    } else {
      int newYCoordinate = runningGameView.getHeight() - groundHeight - bmp.getHeight();
      return new Spike(runningGameView, bmp, xCoordinate, newYCoordinate);
    }
  }
}
