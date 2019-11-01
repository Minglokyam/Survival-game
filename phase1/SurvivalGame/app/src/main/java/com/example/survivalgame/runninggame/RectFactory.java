package com.example.survivalgame.runninggame;

import android.graphics.Rect;

class RectFactory {
  Rect createRect(int left, int top, int right, int bottom) {
    return new Rect(left, top, right, bottom);
  }
}
