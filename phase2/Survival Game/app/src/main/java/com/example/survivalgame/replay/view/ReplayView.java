package com.example.survivalgame.replay.view;

public interface ReplayView {
  void clearCanvas();

  void lockCanvas();

  void unlockCanvasAndPost();

  void toBeforeDodge();

  void drawCircle(float xCoordinate, float yCoordinate, float radius);

  void drawRect(float xCoordinate, float yCoordinate, float width, float height);

  void drawText(String string, float xCoordinate, float yCoordinate);

  void drawColor(int red, int green, int blue);
}
