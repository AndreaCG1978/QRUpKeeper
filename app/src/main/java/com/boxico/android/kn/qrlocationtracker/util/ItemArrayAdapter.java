package com.boxico.android.kn.qrlocationtracker.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.boxico.android.kn.qrlocationtracker.ItemDto;
import com.boxico.android.kn.qrlocationtracker.MainActivity;
import com.boxico.android.kn.qrlocationtracker.R;

import java.util.List;

import androidx.annotation.NonNull;

public class ItemArrayAdapter extends ArrayAdapter<ItemDto> {

    private MainActivity mainActivity = null;

    public ItemArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
        mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = super.getView(position, convertView, parent);
        ListView lv = (ListView)parent;
        ItemDto item = (ItemDto) lv.getAdapter().getItem(position);
        TextView tv;
        LinearLayout ll;
        ll = (LinearLayout)v;
        tv = ll.findViewById(R.id.textItem);
      /*  double distance = distance(item.getLatitude(), mainActivity.getLatitude(), item.getLongitude(), mainActivity.getLongitude(), 0.0,0.0);
        tv.setText(item.getName() + "(" + item.getLatitude() + "," + item.getLongitude() + ") - DISTANCIA PTO ACTUAL=" + distance);*/
        return ll;
    }


    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
