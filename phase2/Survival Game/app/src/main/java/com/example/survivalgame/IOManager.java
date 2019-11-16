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

import java.util.HashMap;

public class IOManager {
  /* the main activity of the game */
  private static Context context;

  /* the file that stores all user's data */
  private static final String USER_FILE = "user_file.ser";

  /** set the main activity of the game */
  public static void setMainActivity(Context newContext) {
    context = newContext;
  }

  /** read the user file and update the current userManager */
  public static void loadFile() {
    FileInputStream fis = null;
    try {
      InputStream inputStream = context.openFileInput(USER_FILE);
      if (inputStream != null) {
        ObjectInputStream input = new ObjectInputStream(inputStream);
        UserManager.setUserMap((HashMap) input.readObject());
        inputStream.close();
      }
    } catch (FileNotFoundException e) {
      Log.e("login activity", "File not found: " + e.toString());
    } catch (IOException e) {
      Log.e("login activity", "Can not read file: " + e.toString());
    } catch (ClassNotFoundException e) {
      Log.e("login activity", "File contained unexpected data type: " + e.toString());
    } finally {
      try {
        if (fis != null) fis.close();
      } catch (IOException e) {
        Log.e("Exception", "File write failed: " + e.toString());
      }
    }
  }

  /** replace the user file with a new file containing the latest user data */
  public static void saveFile() {
    FileOutputStream fos = null;
    try {
      fos = context.openFileOutput(USER_FILE, Context.MODE_PRIVATE);
      ObjectOutputStream os = new ObjectOutputStream(fos);
      os.writeObject(UserManager.getUserMap());
      os.close();
    } catch (IOException e) {
      Log.e("Exception", "File write failed: " + e.toString());
    } finally {
      try {
        if (fos != null) fos.close();
      } catch (IOException e) {
        Log.e("Exception", "File write failed: " + e.toString());
      }
    }
  }
}
