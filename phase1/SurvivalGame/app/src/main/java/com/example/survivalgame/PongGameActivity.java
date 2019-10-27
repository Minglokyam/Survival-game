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

    public void toDodge(){
        Intent intent = new Intent(this, DodgeGameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void toMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
