package com.example.productivitylauncher;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ChangeAppsAdapter extends ArrayAdapter<String> {

    DataLoader readwriter;
    ArrayList<String> inaccessible_apps;

    public ChangeAppsAdapter(Context context, ArrayList<String> apps) {
        super(context, 0, apps);

        readwriter = new DataLoader(context);
        inaccessible_apps = readwriter.getAllInaccessible();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inaccessible_apps = readwriter.getAllInaccessible();
        // Get the data item for this position
        String app = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.second_app_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        // Populate the data into the template view using the data object
        tvLabel.setText(app);
        if(inaccessible_apps.contains(app)){
            tvLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        } else {
            tvLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }

        // Return the completed view to render on screen
        return convertView;
    }


}