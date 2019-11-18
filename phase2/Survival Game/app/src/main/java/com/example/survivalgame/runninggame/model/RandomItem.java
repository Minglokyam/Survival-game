package com.example.survivalgame.runninggame.model;

// We understand that importing Rect in model will violate MVP rule. The model should not have
// android class. We can solve it by creating our own Rect class. But this will be redundant and
// unnecessarily increase the number of classes.
import android.graphics.Rect;

import java.util.List;

public interface RandomItem {
  boolean checkCollision(Rect runner, Rect randomItem);

  Rect getRect();

  boolean outOfScreen();

  int getXCoordinate();

  int getYCoordinate();

  void update();

  List<Integer> getBmpSizeList();

  int getCurrentPosition();
}
