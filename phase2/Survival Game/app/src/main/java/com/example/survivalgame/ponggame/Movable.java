package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

interface Movable {
    void draw(Canvas canvas);

    void move(long fps);
}
