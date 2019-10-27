package com.example.survivalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private UserManager Manager;

    public static User user;

    private final String USER_FILE = "user_file.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.Manager = new UserManager();
        System.out.println(Manager);;
        System.out.println(getFilesDir());
        loadFile(USER_FILE);
        System.out.println(Manager);
//        System.out.println("size:");
//        System.out.println(userManager.numUsers());
    }

    private void loadFile(String fileName){

        FileInputStream fis = null;

        try {
            fis= this.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            this.Manager = (UserManager) is.readObject();
            System.out.println("size:");
            System.out.println(Manager.numUsers());
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            os.writeObject(this.Manager);
            System.out.println("size:");
            System.out.println(Manager.numUsers());
            Toast.makeText(MainActivity.this,"saved to:"+getFilesDir()+USER_FILE, Toast.LENGTH_LONG).show();
            os.close();
            fos.close();

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
            if(!Manager.userExists(username)){
                User newUser = new User(username, password);
                Manager.addUser(newUser);
                saveFile(USER_FILE);
//                String msg = "User creation successful";
//                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }else{
                String msg = "User already exists";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }else{
            String msg = "Username/password cannot be empty";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        System.out.println("size:");
        System.out.println(Manager.numUsers());

    }

    public void logIn(View view){
        loadFile(USER_FILE);
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(!(username.trim().equals("") || password.trim().equals(""))){
            if(Manager.userExists(username)){
                 User temp = Manager.getUser(username);
                if(temp.getUsername().equals(username) && temp.getPassword().equals(password)){
                    System.out.println("login success");
                    user = temp;
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
        if(gameStage == 0){
            Intent toJumpGame = new Intent(this, RunningGameActivity.class);
            toJumpGame.putExtra("userID", user);
            System.out.println("ready to launch");
            startActivity(toJumpGame);
        }
        else if(gameStage == 1){
            Intent toPongGame = new Intent(this, PongGameActivity.class);
            toPongGame.putExtra("userID", user);
            System.out.println("ready to launch");
            startActivity(toPongGame);
        }
        else if(gameStage == 2){
            Intent toDodgeGame = new Intent(this, DodgeGameActivity.class);
            toDodgeGame.putExtra("userID", user);
            System.out.println("ready to launch");
            startActivity(toDodgeGame);
        }
    }
}
