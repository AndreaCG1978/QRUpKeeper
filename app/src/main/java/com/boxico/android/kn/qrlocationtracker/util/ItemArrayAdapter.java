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
import com.boxico.android.kn.qrlocationtracker.R;

import java.util.List;

import androidx.annotation.NonNull;

public class ItemArrayAdapter extends ArrayAdapter<ItemDto> {

    public ItemArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
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
        tv.setText(item.getName());
        return ll;
    }
}
