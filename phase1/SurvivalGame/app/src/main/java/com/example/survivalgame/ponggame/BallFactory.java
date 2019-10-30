package com.example.survivalgame.ponggame;

import com.example.survivalgame.User;

class BallFactory {
    Ball createBall(PongGameManager pongGameManager,
         float radius,
         float xCoordinate,
         float yCoordinate,
         float xVelocity,
         float yVelocity,
         User user){
        return new Ball(pongGameManager,
        radius,
        xCoordinate,
        yCoordinate,
        xVelocity,
        yVelocity,
        user);
    }
}
