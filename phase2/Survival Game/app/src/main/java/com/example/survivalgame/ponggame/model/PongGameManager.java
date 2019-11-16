package com.example.survivalgame.ponggame.model;

import com.example.survivalgame.User;

import java.util.ArrayList;
import java.util.List;

public class PongGameManager {
  private int screenWidth;
  private int screenHeight;
  private BallFactory ballFactory = new BallFactory();
  private RectPaddleFactory rectPaddleFactory = new RectPaddleFactory();

  private RectPaddle rectPaddle;

  private List<Movable> movableList = new ArrayList<>();
  private List<PongGameItem> gameItemList = new ArrayList<>();

  public PongGameManager(User user, int screenWidth, int screenHeight) {
    Ball ball =
        ballFactory.createBall(
            this, 10, screenWidth / 2, screenHeight / 2, screenWidth / 3, -screenHeight / 3, user);
    movableList.add(ball);
    gameItemList.add(ball);
    rectPaddle =
        rectPaddleFactory.createRectPaddle(
            this,
            screenWidth * 2 / 9,
            screenWidth / 4,
            screenHeight / 25,
            screenWidth / 6,
            screenHeight * 7 / 8);
    movableList.add(rectPaddle);
    gameItemList.add(rectPaddle);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  RectPaddle getRectPaddle() {
    return this.rectPaddle;
  }

  public void update(long fps) {
    for (Movable movable : movableList) {
      movable.move(fps);
    }
  }

  public void paddleMoveLeft() {
    rectPaddle.moveLeft();
  }

  public void paddleMoveRight() {
    rectPaddle.moveRight();
  }

  public void paddleStop() {
    rectPaddle.stop();
  }

  public float getTouchReference() {
    float newTouchReference = rectPaddle.getXCoordinate() + rectPaddle.getWidth() / 2;
    return newTouchReference;
  }

  int getScreenWidth() {
    return screenWidth;
  }

  int getScreenHeight() {
    return screenHeight;
  }

  public List<List<Float>> getItemList() {
    List<List<Float>> itemList = new ArrayList<>();
    for (PongGameItem pongGameItem : gameItemList) {
      itemList.add(pongGameItem.getFloatList());
    }
    return itemList;
  }
}
