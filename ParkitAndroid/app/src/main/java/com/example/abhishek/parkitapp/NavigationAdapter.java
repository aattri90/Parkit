package com.example.abhishek.parkitapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class NavigationAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<NavigationModel> list;
    LayoutInflater inflater;

    public NavigationAdapter(Context context, ArrayList<NavigationModel> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.navigation_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final NavigationModel currentItem = (NavigationModel) getItem(position);
        viewHolder.name.setText(currentItem.getParkingName());
        viewHolder.doe.setText(currentItem.getDate());
        viewHolder.nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",
                                            Double.valueOf(currentItem.getLatitude()), Double.valueOf(currentItem.getLongitude()), currentItem.getParkingName());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView name, doe, nav;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.textView14);
            doe = (TextView) view.findViewById(R.id.textView15);
            nav = (TextView) view.findViewById(R.id.textView16);
        }
    }

}
