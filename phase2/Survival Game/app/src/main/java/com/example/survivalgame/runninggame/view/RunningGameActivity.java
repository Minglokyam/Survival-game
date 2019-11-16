package com.example.survivalgame.runninggame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;
import com.example.survivalgame.ponggame.view.PongGameActivity;

public class RunningGameActivity extends AppCompatActivity implements RunningActivityInterface {
  private RunningGameView runningGameView;
  private String name;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    user = UserManager.getUser(name);
    user.setGameStage(User.RUNNING);
    IOManager.saveFile(this);
    runningGameView = new RunningGameView(this, this, user);
    setContentView(runningGameView);
  }

  @Override
  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    user.reset();
    IOManager.saveFile(this);
    startActivity(intent);
    finish();
  }

  @Override
  public void toPong() {
    Intent intent = new Intent(this, PongGameActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
