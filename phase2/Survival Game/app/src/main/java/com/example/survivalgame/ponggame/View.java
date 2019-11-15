package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

public interface View {
    Canvas lockCanvas();

//    SurfaceHolder obtainHolder();

    void unlockCanvasAndPost(Canvas canvas);

    void toMain();

    void toDodge();

    void setTouchReference(float newTouchReference);

    void drawCircle();

    void drawRect();
}
