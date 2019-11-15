package com.example.survivalgame.ponggame.model;

import java.util.List;

public abstract class PongGameItem {
    private float shape;

    public static final float CIRCLE = 0f;
    public static final float RECTANGLE = 1f;

    /**
     * The x-coordinate of this PongGameItem
     */
    private float xCoordinate;
    /**
     * The y-coordinate of this PongGameItem
     */
    private float yCoordinate;
    /**
     * An instance of PongGameManager
     */
    private PongGameManager pongGameManager;

    /**
     * Create a PongGameItem.
     *
     * @param xCoordinate the x-coordinate of this PongGameItem
     * @param yCoordinate the y-coordinate of this PongGameItem
     */
    PongGameItem(PongGameManager pongGameManager, float xCoordinate, float yCoordinate) {
        this.pongGameManager = pongGameManager;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public abstract List<Float> getFloatList();

    /**
     * A getter of pongGameManager
     */
    PongGameManager getPongGameManager() {
        return pongGameManager;
    }

    /**
     * A getter of xCoordinate
     */
    public float getXCoordinate() {
        return xCoordinate;
    }

    /**
     * A setter of xCoordinate
     */
    void setXCoordinate(float newXCoordinate) {
        this.xCoordinate = newXCoordinate;
    }

    /**
     * A getter of yCoordinate
     */
    float getYCoordinate() {
        return yCoordinate;
    }

    /**
     * A setter of yCoordinate
     */
    void setYCoordinate(float newYCoordinate) {
        this.yCoordinate = newYCoordinate;
    }

    public float getShape() {
        return shape;
    }

    public void setShape(float newShape) {
        shape = newShape;
    }
}
