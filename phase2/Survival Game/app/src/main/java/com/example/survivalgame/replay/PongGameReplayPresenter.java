package com.example.survivalgame.replay;

import com.example.survivalgame.User;
import com.example.survivalgame.ponggame.model.PongGameItem;
import com.example.survivalgame.ponggame.model.PongGameManager;
import com.example.survivalgame.ponggame.view.View;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PongGameReplayPresenter extends Thread {
    private boolean running;
    private View view;
    private User user;
    /**
     * the countdown of this game
     */
    private Duration pongDuration;

    public PongGameReplayPresenter(View view, User user, int screenWidth, int screenHeight) {
        this.user = user;
        this.view = view;
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
                    view.drawColor(255, 255, 255);
                    checkQuit();
                    List<List<List<Float>>> replayList = user.getReplay();
                    List<List<Float>> itemList = replayList.get(0);
                    user.deleteReplay();
                    for (List<Float> floatList : itemList) {
                        if (floatList.get(0) == PongGameItem.CIRCLE) {
                            view.drawCircle(floatList.get(1), floatList.get(2), floatList.get(3));
                        } else {
                            view.drawRect(floatList.get(1), floatList.get(2), floatList.get(3), floatList.get(4));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                view.unlockCanvasAndPost();
            }


        }
    }


    /**
     * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/
     */
    public void checkQuit() {
        if (user.isEmptyReplay()) { // If replay ends, return to main screen.
            running = false;
            view.toMain();
        }
    }

    public void setRunning(boolean newRunning) {
        running = newRunning;
    }





}
