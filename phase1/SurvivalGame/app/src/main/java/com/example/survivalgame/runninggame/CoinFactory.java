package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;

class CoinFactory {
  Coin createCoin(RunningGameView view, Bitmap bmp, int x, int y) {
    int newY = view.getHeight() - y - Ground.height - bmp.getHeight();
    return new Coin(view, bmp, x, newY);
  }
}
