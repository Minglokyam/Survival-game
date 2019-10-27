package com.example.survivalgame;

import android.graphics.Canvas;

public class GameThread extends Thread {
    private boolean running = false;
    private DodgeGameView gameView;

    public GameThread(DodgeGameView view){
        this.gameView = view;
    }

    public void setRunning(boolean running){
        this.running = running;

    }

    public void run(){
        while(running){
            Canvas c = null;
            try{
                c = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()){
                    gameView.onDraw(c);
                }
            }
            finally{
                if(c !=null){
                    gameView.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}


