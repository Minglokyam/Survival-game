package com.example.survivalgame;

import android.util.Log;
import android.view.SurfaceHolder;

public class PongGameThread extends Thread{

    private boolean playing;

    private PongGameView myView;



    public PongGameThread(PongGameView view) {
        this.myView = view;
    }

    @Override
    public void run() {
        while (playing) {
            long startTime = System.currentTimeMillis();
            if (!myView.getPause()) {
                myView.update();
            }
            myView.draw();
            long timeInterval = System.currentTimeMillis() - startTime;
            if (timeInterval > 1) {
                myView.setFps(1000 / timeInterval);
            }
        }
    }


    void setRun(boolean toRun) {
        playing = toRun;
    }

}
