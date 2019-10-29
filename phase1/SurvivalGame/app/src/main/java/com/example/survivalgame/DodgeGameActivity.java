package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

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
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    dodgeGameView = new DodgeGameView(this, user, size.x, size.y);
    setContentView(dodgeGameView);
  }

  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }
}
