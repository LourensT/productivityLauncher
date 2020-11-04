package com.example.productivitylauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> IMPORTANT_APPS = Arrays.asList("WhatsApp", "Spotify", "Maps", "Albert Heijn", "YourHour", "Clock");
    List<String> UNACCESSIBLE_APPS = Arrays.asList("Chrome", "Discord", "Duo", "Docs", "Files", "Gmail", "Google", "Netflix", "Play Games", "Play Movies & TV", "Play Store", "rif is fun", "Sheets", "Slides", "Snapchat", "Tinder", "YouTube");

    ArrayList<appInfo> apps =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context c = getApplicationContext();
        onFillAppList(c);

        ArrayAdapter<appInfo> itemsAdapter = new AppsAdapter(this, getAppsToDraw(apps, true));

        final ListView mainApps = findViewById(R.id.lvItems);
        mainApps.setAdapter(itemsAdapter);

        mainApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                appInfo app = (appInfo) mainApps.getItemAtPosition(position);

                //open the app
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(app.packageName.toString());
                startActivity(launchIntent);
            }
        });
    }

    public ArrayList<appInfo> getAppsToDraw(ArrayList<appInfo> apps, boolean importantOnly){

        ArrayList<appInfo> appsToDraw = new ArrayList<>();

        if(importantOnly){
            for (appInfo app: apps
            ) {
                if(app.important){
                    appsToDraw.add(app);
                }
            }
        }
        return(appsToDraw);
    }

    public void onSecondAppsButtonClick(View v){
        Intent secondAppIntent = new Intent(this, SecondAppsActivity.class);
        secondAppIntent.putExtra("BUNDLE", apps);
        startActivity(secondAppIntent);
    }

    public void onFillAppList(Context c){

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = c.getPackageManager();
        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
        for (ResolveInfo ri : allApps
        ) {
            appInfo app = new appInfo();
            app.label = ri.loadLabel(pm);
            app.packageName = ri.activityInfo.packageName;
            if(IMPORTANT_APPS.contains(app.label.toString())){
                app.important = true;
                this.apps.add(app);
            } else if(!UNACCESSIBLE_APPS.contains(app.label.toString())){
                this.apps.add(app);
            }
        }

        //sort apps
        Collections.sort(this.apps);

    }

}
