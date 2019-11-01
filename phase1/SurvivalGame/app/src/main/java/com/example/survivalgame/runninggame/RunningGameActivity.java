package com.example.survivalgame.runninggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.R;
import com.example.survivalgame.User;
import com.example.survivalgame.UserUpdater;
import com.example.survivalgame.ponggame.PongGameActivity;

public class RunningGameActivity extends AppCompatActivity {
  private RunningGameView runningGameView;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_runninggame);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("user");
    UserUpdater.updateUser(user, User.RUNNING);
    IOManager.saveFile();
    runningGameView = new RunningGameView(this, user);
    setContentView(runningGameView);
  }

  @Override
  protected void onPause() {
    super.onPause();
    runningGameView.getRunningGameThread().setRunning(false);
    toPong();
    finish();
  }

  public void toPong() {
    Intent intent = new Intent(this, PongGameActivity.class);
    intent.putExtra("user", user);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
    return;
  }

  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    UserUpdater.resetUser(user);
    IOManager.saveFile();
    startActivity(intent);
    finish();
    return;
  }
}
