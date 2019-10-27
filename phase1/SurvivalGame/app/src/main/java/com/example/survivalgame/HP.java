package com.example.survivalgame;
import android.graphics.*;

public class HP implements instance{
    private Paint paint;
    private int length;
    private int hp;
    HP(){
        paint = new Paint();
        paint.setColor(Color.GREEN);
        length = 600;
        hp = 100;
    }
    public void draw(Canvas c) {
        RectF hpBar = new RectF(DodgeGameActivity.WIDTH - 10, 10, DodgeGameActivity.WIDTH - 110, 10 + this.length);
        //RectF hpBar = new RectF(10, 10, 110, 10 + this.length);
        c.drawRect(hpBar, paint);
    }

    public void update(Canvas canvas) {
        if (hp >= 0){ length = 6 * hp + 5; } else{
            length = 0;
        }
    }
    public int getHp(){ return this.hp; }
    public void setHp(int hp){ this.hp = hp; }
    public int getX(){ return 10; };
    public int getY(){ return 10; };
    public Rect getRect(){ return new Rect(10,10,40,10 + this.length); };


}

