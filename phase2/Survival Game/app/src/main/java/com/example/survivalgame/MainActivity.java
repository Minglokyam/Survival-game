package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.survivalgame.dodgegame.DodgeGameActivity;
import com.example.survivalgame.ponggame.PongGameActivity;
import com.example.survivalgame.runninggame.RunningGameActivity;

public class MainActivity extends AppCompatActivity implements LoginView {
  private LoginPresenter presenter;

  /** Create new UserManager when this activity is created */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    IOManager.setMainActivity(this);
    setContentView(R.layout.activity_main);
    findViewById(R.id.loginButton).setOnClickListener(v -> validateLoginCredentials());
    findViewById(R.id.loginButton).setOnClickListener(v -> validateRegisterCredentials());

    presenter = new LoginPresenter(this, new LoginInteractor());
  }

  private void validateLoginCredentials() {
      presenter.validateLoginCredentials(getName(), getPassword());
  }

  private void validateRegisterCredentials() {
      presenter.validateRegisterCredentials(getName(), getPassword());
  }

  @Override
  public void toGame() {

  }

  @Override
  public void setEmptyInputError() {

  }

  @Override
  public void setUserExistError() {

  }

  @Override
  public void setCredentialsError() {

  }

  /** direct the user to the game he left off */
  public void launchGame(String name, User user) {
    int gameStage = user.getGameStage();
    if (gameStage == User.RUNNING) {
      Intent toJumpGame = new Intent(this, RunningGameActivity.class);
      toGame(toJumpGame);
    } else if (gameStage == User.PONG) {
      Intent toPongGame = new Intent(this, PongGameActivity.class);
      toGame(toPongGame);
    } else if (gameStage == User.DODGE) {
      Intent toDodgeGame = new Intent(this, DodgeGameActivity.class);
      toGame(toDodgeGame);
    }
  }

  /** send the logged in user to another game and start that game's activity */
  private void toGame(Intent intent) {
    intent.putExtra("user", name);
    System.out.println("ready to launch");
    startActivity(intent);
  }

  /**
   * get the username input
   *
   * @return the username
   */
  private String getName() {
    EditText usernameInput = findViewById(R.id.usernameInput);
    return usernameInput.getText().toString();
  }

  /**
   * get the password input
   *
   * @return user's password
   */
  private String getPassword() {
    EditText passwordInput = findViewById(R.id.passwordInput);
    return passwordInput.getText().toString();
  }


}
