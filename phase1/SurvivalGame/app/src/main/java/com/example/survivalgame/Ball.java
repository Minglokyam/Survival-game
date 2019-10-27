package com.example.survivalgame;

import android.graphics.Color;

public class Ball extends GameItemCircle {
    /**
     * The horizontal velocity of this ball
     */
    private float xVelocity;

    /**
     * The vertical velocity of this ball
     */
    private float yVelocity;

    /**
     * The initial horizontal velocity of this ball
     */
    private float initialXVelocity;

    /**
     * The initial vertical velocity of this ball
     */
    private float initialYVelocity;

    /**
     * The initial x-coordinate of this ball
     */
    private float initialXCoordinate;

    /**
     * The initial y-coordinate of this ball
     */
    private float initialYCoordinate;

    User user;

    /**
     * A getter of xVelocity
     */
    public float getXVelocity() {
        return xVelocity;
    }

    /**
     * A setter of xVelocity
     */
    public void setXVelocity(float newXVelocity) {
        this.xVelocity = newXVelocity;
    }

    /**
     * A getter of yVelocity
     */
    public float getYVelocity() {
        return yVelocity;
    }

    /**
     * A setter of yVelocity
     */
    public void setYVelocity(float newYVelocity) {
        this.yVelocity = newYVelocity;
    }

    public Ball(PongGameManager pongGameManager, float radius, float xCoordinate, float yCoordinate, float xVelocity, float yVelocity, User user) {
        super(pongGameManager, radius, xCoordinate, yCoordinate);
        this.user = user;
        initialXVelocity = xVelocity;
        initialYVelocity = yVelocity;
        initialXCoordinate = xCoordinate;
        initialYCoordinate = yCoordinate;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        getPaint().setColor(Color.MAGENTA);
    }

    public void move(long fps) {
        hitTop();
        hitLeft();
        hitRight();
        hitPaddle();
        hitBottom();
        xMove(fps);
        yMove(fps);
    }

    public void xMove(long fps) {
        float newXCoordinate = getXCoordinate() + (xVelocity / fps);
        setXCoordinate(newXCoordinate);
    }

    public void yMove(long fps) {
        float newYCoordinate = getYCoordinate() + (yVelocity / fps);
        setYCoordinate(newYCoordinate);
    }

    private void moveUp(){
        yVelocity = -Math.abs(yVelocity);
    }
    private void moveDown(){
        yVelocity = Math.abs(yVelocity);
    }
    private void moveLeft(){
        xVelocity = -Math.abs(xVelocity);
    }
    private void moveRight(){
        xVelocity = Math.abs(xVelocity);
    }

    private void hitTop() {
        if (getYCoordinate() - getRadius() <= 0) {
            moveDown();
        }
    }

    private void hitLeft() {
        if (getXCoordinate() - getRadius() <= 0) {
            moveRight();
        }
    }

    private void hitRight() {
        if (this.getXCoordinate() + getRadius() >= getPongGameManager().getScreenWidth()) {
            moveLeft();
        }
    }

    private void hitPaddle() {
        if (checkHitPaddle()) {
            moveUp();
        }
    }

    private boolean checkHitPaddle() {
        RectPaddle rectPaddle = getPongGameManager().getRectPaddle();
        return getXCoordinate() >= rectPaddle.getXCoordinate() &&
                getXCoordinate() <= rectPaddle.getXCoordinate() + rectPaddle.getWidth() &&
                getYCoordinate() + getRadius() >= rectPaddle.getYCoordinate();
    }

    private void hitBottom() {
        if (this.getYCoordinate() + getRadius() >= getPongGameManager().getScreenHeight()) {
            user.setLife(user.getLife() - 1);
            resetSpeed();
            resetCoordinate();
        }
    }

    private void resetSpeed() {
        xVelocity = initialXVelocity;
        yVelocity = initialYVelocity;
    }

    private void resetCoordinate() {
        setXCoordinate(initialXCoordinate);
        setYCoordinate(initialYCoordinate);
    }
}
