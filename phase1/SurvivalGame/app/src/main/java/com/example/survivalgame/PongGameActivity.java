package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PongGameActivity extends AppCompatActivity {
    PongGameThread pongGameThread;
    PongGameView pongGameView;
    RunningGameView runningGameView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("userID");
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        pongGameView = new PongGameView(this);
        runningGameView = new RunningGameView(this);
//        setContentView(pongGameView);
        setContentView(runningGameView);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        pongGameView.onResume();
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
//        pongGameView.onPause();
        runningGameView.thread.running = false;
        finish();
    }
}
