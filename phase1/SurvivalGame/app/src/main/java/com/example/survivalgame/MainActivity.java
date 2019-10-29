package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    UserManager userManager;

    public User user;

    private final String USER_FILE = "user_file.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        setContentView(R.layout.activity_main);

        System.out.println(getFilesDir());
    }

    private void loadFile(String fileName){

        FileInputStream fis = null;

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                userManager.userList = (ArrayList) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }finally {
        try {
            if (fis != null) fis.close();
            }catch(IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }

    public void saveFile(String fileName) {


        FileOutputStream fos = null;

        try {
            fos = this.openFileOutput(fileName, this.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            userManager.printList();
            os.writeObject(userManager.userList);
            os.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }finally {
            try {
                if (fos != null) fos.close();
            }catch(IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }

    public void register(View view){
        loadFile(USER_FILE);
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(!(username.trim().equals("") || password.trim().equals(""))){
            if(!userManager.userExists(username)){
                User newUser = new User(username, password, userManager.numUsers());
                userManager.addUser(newUser);
                saveFile(USER_FILE);
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
        loadFile(USER_FILE);
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
