package com.example.survivalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;
import android.view.SurfaceView;
import android.content.Intent;
public class DodgeGameView extends SurfaceView{
    private HP hp;
    private GameThread gameThread;
    private SurfaceHolder holder;
    private GenerateEnemy enemyGen;
    public static List<instance> shells;
    public Paint paint;
    public Plane player;

    public DodgeGameView(Context context){
        super(context);
        hp = new HP();
        player = new Plane();
        shells = new ArrayList<>();
        enemyGen = new GenerateEnemy();
        gameThread = new GameThread(this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        holder = getHolder();
        shells.add(hp);
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
    public void update(Canvas canvas) throws IndexOutOfBoundsException{
        enemyGen.Generate();
        for(int i=0;i<shells.size();i++){
            if(shells.get(i).getY() > (DodgeGameActivity.HEIGHT + 100)){   // This statement removes the object outside the screen.
                shells.remove(i);
            }
            if(shells.get(i).getRect().intersect(player.getRect())){   //COLLIDES, Player is destroyed
                if(shells.get(i) != player && shells.get(i) != hp){
                    if(player.getHp() > 0){
                        player.setHp(player.getHp() - 10);
                        hp.setHp(player.getHp() - 10);
                        shells.remove(shells.get(i));
                        //VIBRATION IMPLEMENTATION?
                    }
                    shells.get(i).update(canvas);
                }
            }

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
