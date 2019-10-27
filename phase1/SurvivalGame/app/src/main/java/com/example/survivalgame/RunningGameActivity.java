package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RunningGameActivity extends AppCompatActivity {

    RunningGameView runningGameView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_game);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("userID");

        runningGameView = new RunningGameView(this);
        setContentView(runningGameView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        runningGameView.thread.running = false;
        finish();
    }
}

