package com.example.survivalgame.dodgegame.view;

public interface DodgeView {
  void lockCanvas();

  void unlockCanvasAndPost();

  void toScoreBoard();

  void toMain();

  void clearCanvas();

  void drawText(String string, float xCoordinate, float yCoordinate);

  void drawOval(float xCoordinate, float yCoordinate);

  void drawRectF(float xCoordinate, float yCoordinate, float width, float length);

  void drawPath(float xCoordinate, float yCoordinate);

  void drawColor(int red, int green, int blue);
}
