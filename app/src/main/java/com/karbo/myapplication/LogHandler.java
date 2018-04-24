package com.karbo.myapplication;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LogHandler {

    public static void saveMessageToLog(String message, Context context) {
        try {
            File file = new File(context.getFilesDir(),"log.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((message + "\n").getBytes());

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static ArrayList<String> getAllMessagesFromLog(Context context) {

        ArrayList<String> allMessagesList = new ArrayList<>();

        File file = new File(context.getFilesDir(),"log.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String tempString = "";
            while ((tempString = bufferedReader.readLine()) != null) {
                allMessagesList.add(tempString);
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return allMessagesList;
    }
}