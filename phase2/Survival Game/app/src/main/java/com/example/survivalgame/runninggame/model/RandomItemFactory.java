package com.example.survivalgame.runninggame.model;

import com.example.survivalgame.runninggame.model.Coin;
import com.example.survivalgame.runninggame.model.RandomItem;
import com.example.survivalgame.runninggame.model.Spike;

import java.util.List;

class RandomItemFactory {
  final int COIN = 0;
  final int SPIKE = 1;

  /** the factory used to create a running game random item. */
  RandomItem createRandomItem(
      int xCoordinate,
      int yCoordinate,
      int movingSpeed,
      List<Integer> bmpSizeList,
      int groundHeight,
      int screenWidth,
      int screenHeight,
      int itemType) {
    if (itemType == COIN) {
      return new Coin(
          xCoordinate,
          yCoordinate,
          movingSpeed,
          bmpSizeList,
          screenWidth,
          screenHeight,
          groundHeight);
    } else {
      return new Spike(
          xCoordinate, yCoordinate, movingSpeed, bmpSizeList, screenHeight, groundHeight);
    }
  }
}
