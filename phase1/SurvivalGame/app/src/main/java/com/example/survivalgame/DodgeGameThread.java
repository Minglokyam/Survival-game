package com.example.survivalgame;

import android.graphics.Canvas;

public class DodgeGameThread extends Thread {
    private boolean running = false;
    private DodgeGameView dodgeGameView;

    public DodgeGameThread(DodgeGameView view){
        this.dodgeGameView = view;
    }

    public void setRunning(boolean running){
        this.running = running;

    }

    public void run(){
        while(running){
            Canvas canvas = null;
            try{
                canvas = dodgeGameView.getHolder().lockCanvas();
                synchronized (dodgeGameView.getHolder()){
                    dodgeGameView.update(canvas);
                    dodgeGameView.draw(canvas);
                }
            }
            finally{
                if(canvas !=null){
                    dodgeGameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}


