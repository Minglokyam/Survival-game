package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class ground {

    // the first and second coordinate of the ground.
    private int x;
    private int y;

    //
    static int height;

    private Bitmap bmp;
    private RunningGameView view;

    ground(RunningGameView view, Bitmap bmp, int x, int y) {
        this.view = view;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        ground.height = bmp.getHeight();
    }

    void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, x, view.getHeight() - bmp.getHeight(), null);
    }
}

