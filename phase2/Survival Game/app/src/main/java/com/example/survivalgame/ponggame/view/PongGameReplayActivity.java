package com.example.survivalgame.ponggame.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;
import com.example.survivalgame.dodgegame.DodgeGameActivity;

public class PongGameReplayActivity extends AppCompatActivity implements ActivityInterface {
    private PongGameReplayView replayView;
    private String name;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra("user");
        user = UserManager.getUser(name);
        replayView = new PongGameReplayView(this, this, user);
        setContentView(replayView);
    }

    /**
     * sent user statistic to DodgeGame, start DodgeGame, end PongGame
     */
    @Override
    public void toDodge() {
        Intent intent = new Intent(this, DodgeGameActivity.class);
        intent.putExtra("user", name);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * reset user statistic, start MainActivity, end PongGame
     */
    @Override
    public void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        user.reset();
        IOManager.saveFile();
        startActivity(intent);
        finish();
    }
}
