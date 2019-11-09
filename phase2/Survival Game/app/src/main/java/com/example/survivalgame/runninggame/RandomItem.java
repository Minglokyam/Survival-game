package com.example.survivalgame.runninggame;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface RandomItem {
  boolean checkCollision(Rect runner, Rect randomItem);

  Rect getRect();

  boolean outOfScreen();

  void draw(Canvas canvas);
}
