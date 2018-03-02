package com.example.abhishek.parkitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ListModel> list;
    LayoutInflater inflater;

    public ReservationAdapter(Context context, ArrayList<ListModel> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.res_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListModel currentItem = (ListModel) getItem(position);
        viewHolder.p_name.setText(currentItem.getP_name());
        viewHolder.p_address.setText(currentItem.getP_address());
        viewHolder.p_abbr.setText(currentItem.getP_abbr());

        return convertView;
    }

    private class ViewHolder {
        TextView p_name, p_address, p_abbr;

        public ViewHolder(View view) {
            p_name = (TextView) view.findViewById(R.id.textView8);
            p_address = (TextView) view.findViewById(R.id.textView9);
            p_abbr = (TextView) view.findViewById(R.id.textView7);
        }
    }
}
