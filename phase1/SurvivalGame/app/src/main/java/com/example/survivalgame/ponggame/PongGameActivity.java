package com.example.survivalgame.ponggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.survivalgame.dodgegame.DodgeGameActivity;
import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;
import com.example.survivalgame.UserUpdater;

public class PongGameActivity extends AppCompatActivity {
  private PongGameView pongGameView;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("user");
    UserUpdater.updateUser(user, User.PONG);
    IOManager.saveFile();
    pongGameView = new PongGameView(this, user);
    setContentView(pongGameView);
  }

  /** sent user statistic to DodgeGame, start DodgeGame, end PongGame*/
  public void toDodge() {
    Intent intent = new Intent(this, DodgeGameActivity.class);
    intent.putExtra("user", user);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
    return;
  }

  /** reset user statistic, start MainActivity, end PongGame*/
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
