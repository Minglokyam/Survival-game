package com.example.survivalgame.ponggame;

class RectPaddleFactory {
  RectPaddle createRectPaddle(
      PongGameManager pongGameManager,
      float xSpeed,
      float width,
      float height,
      float xCoordinate,
      float yCoordinate) {
    return new RectPaddle(pongGameManager, xSpeed, width, height, xCoordinate, yCoordinate);
  }
}
