package com.example.survivalgame;

import android.util.Log;
import android.view.SurfaceHolder;

public class PongGameThread extends Thread implements SurfaceHolder.Callback{
    private boolean playing;
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        playing = true;
        start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {


        playing = false;
        try {
            join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}
}
