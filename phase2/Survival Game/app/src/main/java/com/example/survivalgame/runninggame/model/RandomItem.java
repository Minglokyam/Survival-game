package com.example.survivalgame.runninggame.model;

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
