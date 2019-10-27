package com.example.survivalgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public class Plane implements instance {
    private int counter;
    private boolean hit;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private Paint paint;
    public Plane(){
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        this.x = DodgeGameActivity.WIDTH/2;
        this.y = DodgeGameActivity.HEIGHT - 600; // max = 1800
        this.hit = false;
        counter = 0;
    }

    public void draw(Canvas c){
        if(!hit) {
            Path p = new Path();
            p.moveTo(this.x, this.y);
            p.lineTo(this.x - 60, this.y + 200);
            p.lineTo(this.x, this.y + 120);
            p.lineTo(this.x + 60, this.y + 200);
            p.lineTo(this.x, this.y);
            c.drawPath(p, paint);
            x += xSpeed;
            y += ySpeed;

        }else if(counter%20 <= 10){
            counter++;
            Path p = new Path();
            p.moveTo(this.x, this.y);
            p.lineTo(this.x - 60, this.y + 200);
            p.lineTo(this.x, this.y + 120);
            p.lineTo(this.x + 60, this.y + 200);
            p.lineTo(this.x, this.y);
            paint.setColor(Color.RED);
            c.drawPath(p, paint);
        }else if(counter%20 > 10){
            counter++;
            System.out.println("==========================");
            System.out.println(counter);

            Path p = new Path();
            p.moveTo(this.x, this.y);
            p.lineTo(this.x - 60, this.y + 200);
            p.lineTo(this.x, this.y + 120);
            p.lineTo(this.x + 60, this.y + 200);
            p.lineTo(this.x, this.y);
            paint.setColor(Color.YELLOW);
            c.drawPath(p, paint);
        }
    }

    public void update(Canvas canvas){
        xSpeed *= 0.5;
        ySpeed *= 0.5;
    }
    public Rect getRect(){ return new Rect(x-60,y,x+60,y+200); }

    //========= Getter and setters ===========
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getxSpeed() { return xSpeed; }
    public int getySpeed() { return ySpeed; }
    public void setxSpeed(int xSpeed) { this.xSpeed = xSpeed; }
    public void setySpeed(int ySpeed) { this.ySpeed = ySpeed; }
    public void setHit(boolean hit) { this.hit = hit; }
}
