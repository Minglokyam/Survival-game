package com.example.survivalgame.runninggame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.beforepong.BeforePongActivity;
import com.example.survivalgame.general.IOManager;
import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.uploadactivity.model.UploadActivity;

public class RunningGameActivity extends AppCompatActivity implements RunningActivityInterface {
  private RunningGameView runningGameView;
  private UserManagerSingleton userManagerSingleton;
  private String name;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    userManagerSingleton = UserManagerSingleton.getInstance();
    user = userManagerSingleton.getUser(name);
    user.setGameStage(User.RUNNING);
    IOManager.saveFile(this);
    runningGameView = new RunningGameView(this, this, user);
    setContentView(runningGameView);
  }

  @Override
  public void toPong() {
    Intent intent = new Intent(this, BeforePongActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

  @Override
  public void toUpload() {
    Intent intent = new Intent(this, UploadActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
