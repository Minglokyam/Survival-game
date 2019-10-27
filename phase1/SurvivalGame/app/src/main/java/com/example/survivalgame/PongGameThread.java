package com.example.survivalgame;

public class PongGameThread extends Thread {

    private boolean playing = true;

    private PongGameView myView;

    public PongGameThread(PongGameView view) {
        this.myView = view;
    }

    @Override
    public void run() {
        while (playing) {
            long startTime = System.currentTimeMillis();
            if (!myView.getStop()) {
            myView.update();
            }
            myView.draw();
            long timeInterval = System.currentTimeMillis() - startTime;
            if (!myView.getStop()) {
            User.setTotalDuration(User.getTotalDuration().plusMillis(timeInterval));
            myView.setPongDuration(myView.getPongDuration().minusMillis(timeInterval));
        }
            if (timeInterval > 1) {
                myView.setFps(1000 / timeInterval);
            }
        }
    }

    void setPlaying(boolean newPlaying) {
        playing = newPlaying;
    }
}
