package com.example.survivalgame;

import android.graphics.Color;

public class RectPaddle extends GameItemRect {
    private final int STOP = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    private int movingStatus = STOP;
    private float xSpeed;

    RectPaddle(PongGameManager pongGameManager, float xSpeed, float width, float height, float xCoordinate, float yCoordinate) {
        super(pongGameManager, width, height, xCoordinate, yCoordinate);
        getPaint().setColor(Color.MAGENTA);
        this.xSpeed = xSpeed;
    }

    public void move(long fps) {
        if (!checkHitLeft() && movingStatus == LEFT) {
            xMoveLeft(fps);
        } else if (!checkHitRight() && movingStatus == RIGHT) {
            xMoveRight(fps);
        }
    }

    private void xMoveLeft(long fps) {
        float newXCoordinate = getXCoordinate() - (xSpeed / fps);
        setXCoordinate(newXCoordinate);
    }

    private void xMoveRight(long fps) {
        float newXCoordinate = getXCoordinate() + (xSpeed / fps);
        setXCoordinate(newXCoordinate);
    }

    private boolean checkHitLeft() {
        return getXCoordinate() <= 0;
    }

    private boolean checkHitRight() {
        return getXCoordinate() + getWidth() >= getPongGameManager().getScreenWidth();
    }

    public void moveLeft() {
        movingStatus = LEFT;
    }

    public void moveRight() {
        movingStatus = RIGHT;
    }

    public void stop() {
        movingStatus = STOP;
    }
}
