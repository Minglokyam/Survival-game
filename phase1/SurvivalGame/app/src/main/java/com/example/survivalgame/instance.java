package com.example.survivalgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

abstract interface instance {
  public void draw(Canvas c);

  public void update();

  public int getX();

  public int getY();

  public Rect getRect();
}
