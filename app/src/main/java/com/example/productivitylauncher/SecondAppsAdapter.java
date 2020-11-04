package com.example.productivitylauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondAppsAdapter extends BaseAdapter implements Filterable {

    ArrayList<appInfo> originalValues;
    ArrayList<appInfo> displayedValues;
    LayoutInflater inflater;

    public SecondAppsAdapter(Context context, ArrayList<appInfo> apps) {
        this.originalValues = apps;
        this.displayedValues = apps;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return displayedValues.size();
    }

    @Override
    public appInfo getItem(int position) {
        return this.displayedValues.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //appInfo app = displayedValues.get(position);
        appInfo app = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.second_app_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        // Populate the data into the template view using the data object
        tvLabel.setText(app.label.toString());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                displayedValues = (ArrayList<appInfo>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<appInfo> FilteredArrList = new ArrayList<appInfo>();

                if (originalValues == null) {
                    originalValues = new ArrayList<appInfo>(displayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = originalValues.size();
                    results.values = originalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalValues.size(); i++) {
                        String data = originalValues.get(i).label.toString();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new appInfo(originalValues.get(i).label,originalValues.get(i).packageName, originalValues.get(i).important));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }


}

