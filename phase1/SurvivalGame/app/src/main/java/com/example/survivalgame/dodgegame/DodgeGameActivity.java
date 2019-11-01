package com.example.survivalgame.dodgegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;
import com.example.survivalgame.UserUpdater;

public class DodgeGameActivity extends AppCompatActivity {
  private DodgeGameView dodgeGameView;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("user");
    UserUpdater.updateUser(user, User.DODGE);
    IOManager.saveFile();
    dodgeGameView = new DodgeGameView(this, user);
    setContentView(dodgeGameView);
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
