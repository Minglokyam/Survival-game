package com.example.survivalgame.ponggame.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import com.example.survivalgame.User;
import com.example.survivalgame.ponggame.presenter.PongGamePresenter;

public class PongGameView extends SurfaceView implements View {
    private ActivityInterface activityInterface;

    private Canvas canvas;

    private Paint paintShape;

    private Paint paintText;

    private ArrayList<float[]> replay;

    /**
     * The Thread of this game
     */
    private PongGamePresenter pongGamePresenter;

    /**
     * The screen width
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The screen height
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private float touchReference;

    public PongGameView(Context context, ActivityInterface activityInterface, User user) {
        super(context);
        this.activityInterface = activityInterface;
        paintShape = new Paint();
        paintShape.setColor(Color.MAGENTA);
        paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);

        pongGamePresenter = new PongGamePresenter(this, user, screenWidth, screenHeight);

        getHolder().addCallback(
                new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        pongGamePresenter.setRunning(true);
                        pongGamePresenter.start();
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int a, int b, int c) {
                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                    }
                });
    }

    /**
     * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                // point on the right of the peddle
                if (motionEvent.getX() > touchReference) {
                    pongGamePresenter.paddleMoveRight();
                } else { // point on the left of the peddle
                    pongGamePresenter.paddleMoveLeft();
                }
                break;
            case MotionEvent.ACTION_UP:
                pongGamePresenter.paddleStop();
                break;
        }
        return true;
    }

    @Override
    public void lockCanvas() {
        canvas = getHolder().lockCanvas();
    }

    @Override
    public void unlockCanvasAndPost() {
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void toMain() {
        activityInterface.toMain();
    }

    @Override
    public void toDodge() {
        activityInterface.toDodge();
    }

    @Override
    public void setTouchReference(float newTouchReference) {
        touchReference = newTouchReference;
    }

    @Override
    public void drawCircle(float xCoordinate, float yCoordinate, float radius) {
        canvas.drawCircle(xCoordinate, yCoordinate, radius, paintShape);
    }

    @Override
    public void drawRect(float xCoordinate, float yCoordinate, float width, float height) {
        canvas.drawRect(
                xCoordinate,
                yCoordinate,
                xCoordinate + width,
                yCoordinate + height,
                paintShape);
    }

    @Override
    public void drawText(String string, float xCoordinate, float yCoordinate) {
        canvas.drawText(string, xCoordinate, yCoordinate, paintText);
    }

    @Override
    public void drawColor(int red, int green, int blue) {
        canvas.drawColor(Color.rgb(red, green, blue));
    }

    @Override
    public void clearCanvas() {
        canvas = null;
    }
}
