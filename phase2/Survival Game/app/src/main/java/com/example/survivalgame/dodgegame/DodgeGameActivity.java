package com.example.survivalgame.dodgegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import com.example.survivalgame.R;
import android.os.Bundle;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.User;
import com.example.survivalgame.UserUpdater;
// MainActivity class for the dodge game
public class DodgeGameActivity extends AppCompatActivity {
  private DodgeGameView dodgeGameView;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dodgegame);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("user");
    UserUpdater.updateUser(user, User.DODGE);
    IOManager.saveFile();
    dodgeGameView = new DodgeGameView(this, user);
    setContentView(dodgeGameView);
  }

  /** after finishing the DodgeGame, move back to MainActivity */
  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    UserUpdater.resetUser(user);
    IOManager.saveFile();
    startActivity(intent);
    finish();
  }
}
