package com.example.survivalgame;

public class PongGameThread extends Thread {

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
            User.setTotalDuration(User.getTotalDuration().plusMillis(timeInterval));
            myView.setPongDuration(myView.getPongDuration().minusMillis(timeInterval));
            if (timeInterval > 1) {
                myView.setFps(1000 / timeInterval);
            }
        }
    }

    void setRun(boolean toRun) {
        playing = toRun;
    }
}
