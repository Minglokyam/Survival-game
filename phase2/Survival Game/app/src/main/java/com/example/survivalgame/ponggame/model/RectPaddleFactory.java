package com.example.survivalgame.ponggame.model;

class RectPaddleFactory {
    /**
     * the factory used to create the rect of paddle.
     */
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
