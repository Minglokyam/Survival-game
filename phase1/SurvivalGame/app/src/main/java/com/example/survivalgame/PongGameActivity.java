package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PongGameActivity extends AppCompatActivity {
    PongGameView pongGameView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("userID");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pongGameView = new PongGameView(this);
        setContentView(pongGameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User.setGameStage(User.PONG);
        pongGameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pongGameView.pause();
    }
}
