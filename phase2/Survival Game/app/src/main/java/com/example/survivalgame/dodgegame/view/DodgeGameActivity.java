package com.example.survivalgame.dodgegame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.scoreboard.RankingActivity;
import com.example.survivalgame.UserManager;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;
import com.example.survivalgame.replay.PongGameReplayActivity;

// MainActivity class for the dodge game
public class DodgeGameActivity extends AppCompatActivity implements DodgeActivityInterface {
  private DodgeGameView dodgeGameView;
  private String name;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    user = UserManager.getUser(name);
    user.setGameStage(User.DODGE);
    IOManager.saveFile(this);
    dodgeGameView = new DodgeGameView(this, this, user);
    setContentView(dodgeGameView);
  }

  /** after finishing the DodgeGame, move back to MainActivity */
  @Override
  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    user.reset();
    IOManager.saveFile(this);
    startActivity(intent);
    finish();
  }

  public void toReplay() {
    Intent intent = new Intent(this, PongGameReplayActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

  public void toScoreBoard() {
    Intent intent = new Intent(this, RankingActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
