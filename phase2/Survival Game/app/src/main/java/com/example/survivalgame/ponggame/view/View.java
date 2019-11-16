package com.example.survivalgame.ponggame.view;

public interface View {
  void clearCanvas();

  void lockCanvas();

  void unlockCanvasAndPost();

  void toMain();

  void toDodge();

  void setTouchReference(float newTouchReference);

  void drawCircle(float xCoordinate, float yCoordinate, float radius);

  void drawRect(float xCoordinate, float yCoordinate, float width, float height);

  void drawText(String string, float xCoordinate, float yCoordinate);

  void drawColor(int red, int green, int blue);
}
