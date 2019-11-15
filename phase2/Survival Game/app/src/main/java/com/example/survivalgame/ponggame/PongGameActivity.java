package com.example.survivalgame.ponggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.UserManager;
import com.example.survivalgame.dodgegame.DodgeGameActivity;
import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;

public class PongGameActivity extends AppCompatActivity implements ActivityInterface {
    private PongGameView pongGameView;
    private String name;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra("user");
        user = UserManager.getUser(name);
        user.setGameStage(User.PONG);
        IOManager.saveFile();
        pongGameView = new PongGameView(this, this, user);
        setContentView(pongGameView);
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
