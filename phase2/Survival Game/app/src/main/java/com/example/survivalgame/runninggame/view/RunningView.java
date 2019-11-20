package com.example.survivalgame.runninggame.view;

import android.graphics.Rect;

public interface RunningView {
  void lockCanvas();

  void unlockCanvasAndPost();

  void clearCanvas();

  void drawText(String string, float xCoordinate, float yCoordinate);

  void drawColor(int red, int green, int blue);

  void toScoreBoard();

  void toPong();

  void drawBitmap(String bmpName, int xCoordinate, int yCoordinate);

  void drawBitmap(String bmpName, Rect rectA, Rect rectB);
}
