package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Spikes {

    private int x, y;
    private Bitmap spikesBmp;
    private RunningGameView view;

    Spikes(RunningGameView view, Bitmap spikesBmp, int x) {
        this.view = view;
        this.spikesBmp = spikesBmp;
        this.x = x;
    }

    boolean checkCollision(Rect runner, Rect spikes) {
        return Rect.intersects(runner, spikes);
    }

    Rect GetBounds() {
        return new Rect(this.x, this.y, this.x + spikesBmp.getWidth(), this.y + spikesBmp.getHeight());
    }

    private void Update() {
        x -= RunningGameView.movingSpeed;
        y = view.getHeight() - Ground.height - spikesBmp.getHeight();
    }

    int getX() {
        return x;
    }

    void onDraw(Canvas canvas) {
        Update();
        Rect src = new Rect(0, 0, spikesBmp.getWidth(), spikesBmp.getHeight());
        Rect dst = new Rect(x, y, x + spikesBmp.getWidth(), y + spikesBmp.getHeight());
        canvas.drawBitmap(spikesBmp, src, dst, null);
    }
}

