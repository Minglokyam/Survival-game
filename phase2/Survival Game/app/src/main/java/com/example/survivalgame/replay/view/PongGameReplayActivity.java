package com.example.survivalgame.replay.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;
import com.example.survivalgame.scoreboard.view.RankingActivity;

public class PongGameReplayActivity extends AppCompatActivity implements ReplayActivityInterface {
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

  /** reset user statistic, start MainActivity, end PongGameReplay */
  @Override
  public void toScoreBoard() {
    Intent intent = new Intent(this, RankingActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
