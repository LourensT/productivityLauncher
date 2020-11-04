package com.example.productivitylauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AppsAdapter extends ArrayAdapter<appInfo> {

    public AppsAdapter(Context context, ArrayList<appInfo> apps) {
        super(context, 0, apps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        appInfo app = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        // Populate the data into the template view using the data object
        tvLabel.setText(app.label.toString());
        // Return the completed view to render on screen
        return convertView;
    }


}

