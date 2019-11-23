package com.example.survivalgame.replay.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.dodgegame.view.DodgeGameActivity;

public class PongGameReplayActivity extends AppCompatActivity implements ReplayActivityInterface {
  private PongGameReplayView replayView;
  private UserManagerSingleton userManagerSingleton;
  private String name;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    userManagerSingleton = UserManagerSingleton.getInstance();
    name = intent.getStringExtra("user");
    user = userManagerSingleton.getUser(name);
    replayView = new PongGameReplayView(this, this, user);
    setContentView(replayView);
  }

  /** reset user statistic, start MainActivity, end PongGameReplay */
  @Override
  public void toDodge() {
    Intent intent = new Intent(this, DodgeGameActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
