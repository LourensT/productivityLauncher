package com.example.productivitylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChangeAppsActivity extends AppCompatActivity {

    DataLoader readwriter;
    ArrayList<String> all_apps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_apps);

        readwriter = new DataLoader(getApplicationContext());
        //saveKey("password");
        String key = readwriter.getKey();

        all_apps = getAllApps(this);

        ChangeAppsAdapter adapter = new ChangeAppsAdapter(this, all_apps);
        final ListView lv = findViewById(R.id.lvApps);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                String label = lv.getItemAtPosition(position).toString();
                TextView tvLabel = v.findViewById(R.id.tvLabel);
                if(readwriter.getAllInaccessible().contains(label)){
                    readwriter.removeInaccessible(label);
                    tvLabel.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                } else {
                    readwriter.addInaccessible(label);
                    tvLabel.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                }
            }
        });

        // TODO add yellow color for the favorite apps. Also in the ChangeAppsAdapter


    }

    public ArrayList<String> getAllApps(Context c){

        ArrayList<String> all_apps = new ArrayList<String>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = c.getPackageManager();
        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);

        for (ResolveInfo ri : allApps
        ) {
            all_apps.add(ri.loadLabel(pm).toString());
        }
        //sort apps
        Collections.sort(all_apps);

        return all_apps;
    }
}
