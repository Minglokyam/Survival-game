package com.example.survivalgame.ponggame.model;

import com.example.survivalgame.User;

class BallFactory {
  /**
   * @param pongGameManager the PongGameManager that has this ball
   * @param radius the radius of this ball
   * @param xCoordinate the x-coordinate of this ball
   * @param yCoordinate the y-coordinate of this ball
   * @param xVelocity the horizontal velocity of this ball
   * @param yVelocity the vertical velocity of this ball
   * @param user the user of this game
   * @return return an instance of Ball
   */
  Ball createBall(
      PongGameManager pongGameManager,
      float radius,
      float xCoordinate,
      float yCoordinate,
      float xVelocity,
      float yVelocity,
      User user) {
    return new Ball(pongGameManager, radius, xCoordinate, yCoordinate, xVelocity, yVelocity, user);
  }
}
