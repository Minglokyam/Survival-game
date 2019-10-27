package com.example.survivalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
public class DodgeGameView extends SurfaceView{
    private HP hp;
    private DodgeGameThread dodgeGameThread;
    private SurfaceHolder holder;
    private GenerateEnemy enemyGen;
    public static List<instance> shells;
    public Paint paint;
    public Plane plane;

    public DodgeGameView(Context context){
        super(context);
        hp = new HP();
        plane = new Plane();
        shells = new ArrayList<>();
        enemyGen = new GenerateEnemy();
        dodgeGameThread = new DodgeGameThread(this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        holder = getHolder();
        shells.add(hp);
        shells.add(plane);
        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                dodgeGameThread.setRunning(true);
                dodgeGameThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                dodgeGameThread.setRunning(false);
                while(retry){
                    try{
                        dodgeGameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // This method updates all objects in the arraylist!
    public void update(Canvas canvas){
        enemyGen.Generate();
        for(int i=0;i<shells.size();i++){
            if(shells.get(i).getY() > (DodgeGameActivity.HEIGHT + 100)){   // This statement removes the object outside the screen.
                shells.remove(i);
            }
            if(shells.get(i).getRect().intersect(plane.getRect())){   //COLLIDES, Player is destroyed
                if(shells.get(i) != plane && shells.get(i) != hp){
                    if(plane.getHp() > 0){
                        plane.setHp(plane.getHp() - 10);
                        hp.setHp(plane.getHp() - 10);
                        shells.remove(shells.get(i));
                        //VIBRATION IMPLEMENTATION?
                    }
                }
            }
            shells.get(i).update(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.rgb(255, 255, 255));
        plane.draw(canvas);
        for(int i=0;i<shells.size();i++){
            shells.get(i).draw(canvas);
        }

    }
    public void endGame(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

}
