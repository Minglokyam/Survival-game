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
    presenter = new LoginPresenter(this, new LoginInteractor());
  }

  public void validateLoginCredentials(View view) {
      presenter.validateLoginCredentials(getName(), getPassword());
  }

    public void validateRegisterCredentials(View view) {
      presenter.validateRegisterCredentials(getName(), getPassword());
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

  public void launchRunningGame(String name, User user){
      Intent toJumpGame = new Intent(this, RunningGameActivity.class);
      toGame(name, toJumpGame);
  }

    public void launchPongGame(String name, User user){
        Intent toPongGame = new Intent(this, PongGameActivity.class);
        toGame(name, toPongGame);
    }

    public void launchDodgeGame(String name, User user){
        Intent toDodgeGame = new Intent(this, DodgeGameActivity.class);
        toGame(name, toDodgeGame);
    }

  /** send the logged in user to another game and start that game's activity */
  private void toGame(String username, Intent intent) {
    intent.putExtra("user", username);
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
