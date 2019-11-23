package com.example.survivalgame.dodgegame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.scoreboard.view.RankingActivity;
import com.example.survivalgame.general.UserManager;

import com.example.survivalgame.general.IOManager;
import com.example.survivalgame.general.User;
import com.example.survivalgame.uploadactivity.UploadActivity;

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

  @Override
  public void toScoreBoard() {
    Intent intent = new Intent(this, RankingActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
  public void toUpload() {
    Intent intent = new Intent(this, UploadActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

}
