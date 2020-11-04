package com.example.productivitylauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_apps);

        Intent intent = getIntent();
        //Bundle extras = intent.getExtras();
        //ArrayList<appInfo> apps = extras.getByte(MainActivity.APPS_TAG, new ArrayList<appInfo>());
        //ArrayList<appInfo> apps = intent.getStringArrayListExtra(MainActivity.APPS_TAG);
        //Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<appInfo> apps = new ArrayList<>();
        apps = (ArrayList<appInfo>) intent.getSerializableExtra("BUNDLE");

        final SecondAppsAdapter adapter = new SecondAppsAdapter(this, apps);

        final ListView secondApps = findViewById(R.id.lvItems);
        secondApps.setAdapter(adapter);

        secondApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                appInfo app = (appInfo) secondApps.getItemAtPosition(position);

                //open the app
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(app.packageName.toString());
                startActivity(launchIntent);
            }
        });

        final TextView searchText = findViewById(R.id.searchText);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //Call back the adapter adding charcter to string
                    adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
