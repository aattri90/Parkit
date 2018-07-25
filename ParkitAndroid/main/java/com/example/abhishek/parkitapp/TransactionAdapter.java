package com.example.abhishek.parkitapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<TransactionModel> list;
    LayoutInflater inflater;

    public TransactionAdapter(Context context, ArrayList<TransactionModel> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.trans_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TransactionModel currentItem = (TransactionModel) getItem(position);
        viewHolder.name.setText("Parking : "+currentItem.getParkingName());
        viewHolder.vehno.setText("Vehicle : "+currentItem.getVehicleNo());
        viewHolder.doe.setText("Date : "+currentItem.getDate());
        viewHolder.bill.setText("Rs. "+currentItem.getBill());

        return convertView;
    }

    private class ViewHolder {
        TextView name, vehno, doe, bill;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.textView10);
            vehno = (TextView) view.findViewById(R.id.textView11);
            doe = (TextView) view.findViewById(R.id.textView12);
            bill = (TextView) view.findViewById(R.id.textView13);
        }
    }

}
