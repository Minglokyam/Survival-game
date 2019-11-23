package com.example.survivalgame.ponggame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.general.IOManager;
import com.example.survivalgame.general.User;
import com.example.survivalgame.beforereplay.BeforeReplayActivity;
import com.example.survivalgame.scoreboard.view.RankingActivity;

import java.util.ArrayList;
import java.util.List;

public class PongGameActivity extends AppCompatActivity implements PongActivityInterface {
  private PongGameView pongGameView;
  private String name;
  private UserManagerSingleton userManagerSingleton;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    userManagerSingleton = UserManagerSingleton.getInstance();
    name = intent.getStringExtra("user");
    user = userManagerSingleton.getUser(name);
    user.setGameStage(User.PONG);
    user.setReplay(new ArrayList<List<List<Float>>>()); // empty the replay
    IOManager.saveFile(this);
    pongGameView = new PongGameView(this, this, user);
    setContentView(pongGameView);
  }

  /** sent user statistic to DodgeGame, start DodgeGame, end PongGame */
  @Override
  public void toBeforeReplay() {
    Intent intent = new Intent(this, BeforeReplayActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

  @Override
  public void toScoreBoard() {
    Intent intent = new Intent(this, RankingActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
