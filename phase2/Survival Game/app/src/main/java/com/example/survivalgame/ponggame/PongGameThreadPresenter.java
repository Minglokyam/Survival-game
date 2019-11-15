package com.example.survivalgame.ponggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.survivalgame.User;

import java.time.Duration;

class PongGameThreadPresenter extends Thread {
    private boolean running;
    private View view;
    private User user;
    private PongGameManager pongGameManager;
    private long FPS = 30;
    /**
     * the countdown of this game
     */
    private Duration pongDuration;
    private Paint paintText;

    public PongGameThreadPresenter(View view, User user, int screenWidth, int screenHeight) {
        this.user = user;
        this.view = view;
        pongGameManager = new PongGameManager(user, screenWidth, screenHeight);
        paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        pongDuration = Duration.ofSeconds(30);
    }

    /**
     * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/ and assignment
     * 1 fishtank
     */
    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();

            Canvas canvas = null;
            try {
                canvas = view.lockCanvas();
                synchronized (this) {
                    pongGameManager.update(FPS);
                    setTouchReference();
                    canvas.drawColor(Color.rgb(255, 255, 255));
                    canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
                    canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
                    canvas.drawText("Game time: " + pongDuration.getSeconds(), 0, 96, paintText);
                    canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);
                    checkQuit();
                    pongGameManager.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    view.unlockCanvasAndPost(canvas);
                }
            }

            long timeInterval = System.currentTimeMillis() - startTime;
            // update the total time
            user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
            // update the countdown
            pongDuration = (pongDuration.minusMillis(timeInterval));

            if (timeInterval > 1) {
                FPS = 1000 / timeInterval;
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

    void setRunning(boolean newRunning) {
        running = newRunning;
    }

    void setTouchReference() {
        float newTouchReference = pongGameManager.getTouchReference();
        view.setTouchReference(newTouchReference);
    }

    void paddleMoveLeft() {
        pongGameManager.paddleMoveLeft();
    }

    void paddleMoveRight() {
        pongGameManager.paddleMoveRight();
    }

    void paddleStop() {
        pongGameManager.paddleStop();
    }
}
