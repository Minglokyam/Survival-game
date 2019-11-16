package com.example.survivalgame.ponggame.presenter;

import com.example.survivalgame.User;
import com.example.survivalgame.ponggame.view.View;
import com.example.survivalgame.ponggame.model.PongGameItem;
import com.example.survivalgame.ponggame.model.PongGameManager;

import java.time.Duration;
import java.util.List;

public class PongGamePresenter extends Thread {
    private boolean running;
    private View view;
    private User user;
    private PongGameManager pongGameManager;
    private long fps = 30;
    /**
     * the countdown of this game
     */
    private Duration pongDuration;

    public PongGamePresenter(View view, User user, int screenWidth, int screenHeight) {
        this.user = user;
        this.view = view;
        pongGameManager = new PongGameManager(user, screenWidth, screenHeight);
        pongDuration = Duration.ofSeconds(10);
    }

    /**
     * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ and assignment
     * 1 fishtank
     */
    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();
            view.clearCanvas();
            try {
                view.lockCanvas();
                synchronized (this) {
                    pongGameManager.update(fps);
                    setTouchReference();
                    view.drawColor(255, 255, 255);
                    view.drawText("Life: " + user.getLife(), 0, 32);
                    view.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64);
                    view.drawText("Game time: " + pongDuration.getSeconds(), 0, 96);
                    view.drawText("Score: " + user.getScore(), 0, 128);
                    checkQuit();
                    List<List<Float>> itemList = pongGameManager.getItemList();
                    for (List<Float> floatList : itemList) {
                        if (floatList.get(0) == PongGameItem.CIRCLE) {
                            view.drawCircle(floatList.get(1), floatList.get(2), floatList.get(3));
                        } else {
                            view.drawRect(floatList.get(1), floatList.get(2), floatList.get(3), floatList.get(4));
                        }
                    }

                    // save replay
                    user.addReplay(itemList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                view.unlockCanvasAndPost();
            }

            long timeInterval = System.currentTimeMillis() - startTime;
            // update the total time
            user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
            // update the countdown
            pongDuration = (pongDuration.minusMillis(timeInterval));

            if (timeInterval > 1) {
                fps = 1000 / timeInterval;
            }
        }
    }

    /**
     * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/
     */
    public void checkQuit() {
        user.setScore(user.getScore() + 1);
        if (user.getLife() == 0) { // If no life left, return to main screen.
            running = false;
            view.toMain();
        } else if (pongDuration.getSeconds() <= 0) { // If countdown reach 0, go to next game.
            running = false;
            view.toDodge();
        }
    }

    public void setRunning(boolean newRunning) {
        running = newRunning;
    }

    void setTouchReference() {
        float newTouchReference = pongGameManager.getTouchReference();
        view.setTouchReference(newTouchReference);
    }

    public void paddleMoveLeft() {
        pongGameManager.paddleMoveLeft();
    }

    public void paddleMoveRight() {
        pongGameManager.paddleMoveRight();
    }

    public void paddleStop() {
        pongGameManager.paddleStop();
    }


}
