package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    UserManager userManager;

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        IOManager.setMainActivity(this);
        IOManager.setUserManager();
        setContentView(R.layout.activity_main);
        System.out.println(getFilesDir());
    }

    public void register(View view){
        IOManager.loadFile();
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(!(username.trim().equals("") || password.trim().equals(""))){
            if(!userManager.userExists(username)){
                User newUser = new User(username, password, userManager.numUsers());
                userManager.addUser(newUser);
                IOManager.saveFile();
                String msg = "User creation successful";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }else{
                String msg = "User already exists";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }else{
            String msg = "Username/password cannot be empty";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        System.out.println("size:");
        System.out.println(userManager.numUsers());

    }

    public void logIn(View view){
        IOManager.loadFile();
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(!(username.trim().equals("") || password.trim().equals(""))){
            System.out.println(userManager.userExists(username));
            if(userManager.userExists(username)){
                 User temp = userManager.getUser(username);
                if(temp.getUsername().equals(username) && temp.getPassword().equals(password)){
                    System.out.println("login success");
                    user = temp;
                    UserUpdater.setMainActivity(this);
                    UserUpdater.setUserList();
                    launchGame();
                }else {
                    String msg = "Username/password does not match";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }else {
                String msg = "User does not exist";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }else{
            String msg = "Username/password cannot be empty";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void launchGame() {
        int gameStage = user.getGameStage();
        if(gameStage == User.RUNNING){
            Intent toJumpGame = new Intent(this, RunningGameActivity.class);
            toJumpGame.putExtra("user", user);
            System.out.println("ready to launch");
            startActivity(toJumpGame);
        }
        else if(gameStage == User.PONG){
            Intent toPongGame = new Intent(this, PongGameActivity.class);
            toPongGame.putExtra("user", user);
            System.out.println("ready to launch");
            startActivity(toPongGame);
        }
        else if(gameStage == User.DODGE){
            Intent toDodgeGame = new Intent(this, DodgeGameActivity.class);
            toDodgeGame.putExtra("user", user);
            System.out.println("ready to launch");
            startActivity(toDodgeGame);
        }
    }
}
