package com.example.productivitylauncher;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataLoader {

    String KEY_FILE_NAME = "key.txt";
    String KEY_IMPORTANT_APPS = "important.txt";
    String KEY_unaccessible_APPS = "unaccessible.txt";
    Context c;

    ArrayList<String> inaccessible;

    public DataLoader(Context c){
        this.c = c;
        inaccessible = new ArrayList<>(MainActivity.UNACCESSIBLE_APPS);
    }

    public ArrayList<String> getAllInaccessible(){
        return inaccessible;
        // TODO Figure this shit out with getting it from the file
    }

    public void addInaccessible(String label){
        Log.d("lol", label);
        inaccessible.add(label);
    }

    public void removeInaccessible(String label){
        Log.d("lol", label);
        inaccessible.remove(label);
    }

    public void setKey(String new_key) {
        FileOutputStream fos = null;
        try {
            fos = c.openFileOutput(MainActivity.KEY_FILE_NAME, Context.MODE_PRIVATE);
            fos.write(new_key.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getKey(){
        FileInputStream fis = null;
        String key = "";
        try {
            fis = this.c.openFileInput(MainActivity.KEY_FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text);
            }
            key = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return key;
        }
    }


}
