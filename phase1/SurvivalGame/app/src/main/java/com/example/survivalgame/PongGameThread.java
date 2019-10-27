package com.example.survivalgame;

public class PongGameThread extends Thread {

    private boolean playing = true;

    private PongGameView pongGameView;

    private User user;

    public PongGameThread(PongGameView newPongGameView, User user) {
        this.user = user;
        this.pongGameView = newPongGameView;
    }

    @Override
    public void run() {
        while (playing) {
            long startTime = System.currentTimeMillis();
            if (!pongGameView.getStop()) {
                pongGameView.update();
            }
            pongGameView.draw();
            long timeInterval = System.currentTimeMillis() - startTime;
            if (!pongGameView.getStop()) {
                user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
                pongGameView.setPongDuration(pongGameView.getPongDuration().minusMillis(timeInterval));
            }
            if (timeInterval > 1) {
                pongGameView.setFPS(1000 / timeInterval);
            }
        }
    }

    void setPlaying(boolean newPlaying) {
        playing = newPlaying;
    }
}
