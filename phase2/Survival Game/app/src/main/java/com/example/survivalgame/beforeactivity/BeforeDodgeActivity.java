package com.example.survivalgame.beforeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.survivalgame.R;
import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.dodgegame.view.DodgeGameActivity;

public class BeforeDodgeActivity extends AppCompatActivity {
  private String name;
  private User user;
  private UserManagerSingleton userManagerSingleton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    userManagerSingleton = UserManagerSingleton.getInstance();
    user = userManagerSingleton.getUser(name);

    setContentView(R.layout.activity_before_dodge);
  }

  public void toDodge(View view) {
    Intent intent = new Intent(this, DodgeGameActivity.class);
    intent.putExtra("user", name);
    user.clearReplay();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}
