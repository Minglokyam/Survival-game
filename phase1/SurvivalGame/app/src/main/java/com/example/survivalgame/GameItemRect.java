package com.example.survivalgame;

import android.graphics.Canvas;

public abstract class GameItemRect extends GameItem {
    /**
     * The width of this rectangle
     */
    private float width;
    /**
     * The height of this rectangle
     */
    private float height;

    public GameItemRect(PongGameManager pongGameManager, float width, float height, float xCoordinate, float yCoordinate) {
        super(pongGameManager, xCoordinate, yCoordinate);
        this.width = width;
        this.height = height;
    }

    /**
     * A getter of width
     */
    public float getWidth() {
        return width;
    }

    /**
     * A getter of height
     */
    public float getHeight() {
        return height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(getXCoordinate(), getYCoordinate(), getXCoordinate() + getWidth(), getYCoordinate() + getHeight(), getPaint());
    }
}
