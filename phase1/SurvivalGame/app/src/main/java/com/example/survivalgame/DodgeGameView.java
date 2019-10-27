package com.example.survivalgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;
import android.view.SurfaceView;
public class DodgeGameView extends SurfaceView{
    private GameThread gameThread;
    private SurfaceHolder holder;
    private GenerateEnemy enemyGen;
    public static List<instance> shells;
    public Paint paint;
    public Plane player = new Plane();

    public DodgeGameView(Context context){
        super(context);
        shells = new ArrayList<>();
        enemyGen = new GenerateEnemy();
        gameThread = new GameThread(this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        holder = getHolder();
        shells.add(player);
        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while(retry){
                    try{
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        draw(canvas);
        update(canvas);


    }

    // This method updates all objects in the arraylist!
    public void update(Canvas canvas){
        enemyGen.Generate();
        for(int i=0;i<shells.size();i++){
            if(shells.get(i).getY() > (DodgeGameActivity.HEIGHT + 100)){   // This statement removes the object outside the screen.
                shells.remove(i);
            }
            if(shells.get(i).getRect().intersect(player.getRect())){
                if(shells.get(i) != player){
                    player.setHit(true);
                    shells.remove(player);
                    endGame();
                }
            }
            shells.get(i).update(canvas);
        }
    }
    public void draw(Canvas c){
        for(int i=0;i<shells.size();i++){
            shells.get(i).draw(c);
        }

    }

    public void endGame(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

}
