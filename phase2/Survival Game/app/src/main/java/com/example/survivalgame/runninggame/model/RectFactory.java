package com.example.survivalgame.runninggame.model;

import android.graphics.Rect;

public class RectFactory {
  /** the factory used to create the rect. */
  public Rect createRect(int left, int top, int right, int bottom) {
    return new Rect(left, top, right, bottom);
  }
}
