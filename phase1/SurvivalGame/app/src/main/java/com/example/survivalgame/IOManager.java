package com.example.survivalgame;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IOManager {
    private static MainActivity mainActivity;
    private static UserManager userManager;
    private static final String USER_FILE = "user_file.ser";
    public static void setMainActivity(Context context){
        mainActivity = (MainActivity)context;
    }
    public static void setUserManager(){
        userManager = mainActivity.userManager;
    }

    public static void loadFile(){
        FileInputStream fis = null;
        try {
            InputStream inputStream = mainActivity.openFileInput(USER_FILE);
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

    public static void saveFile() {
        FileOutputStream fos = null;
        try {
            fos = mainActivity.openFileOutput(USER_FILE, mainActivity.MODE_PRIVATE);
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
}
