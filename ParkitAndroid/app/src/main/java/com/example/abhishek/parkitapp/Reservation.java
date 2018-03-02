package com.example.abhishek.parkitapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class Reservation extends AppCompatActivity {

    ListView listView;
    ArrayList<ListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customactionbar_location);

        list = new ArrayList<>();

        LocationFetch lf = new LocationFetch(Reservation.this);
        lf.execute();

        listView = findViewById(R.id.listView1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ListModel lm = list.get(position);
                Toast.makeText(getBaseContext(), "Parking Selected: " + lm.getP_name(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Reservation.this,Checkin.class);
                Bundle extras = new Bundle();
                extras.putString("id",lm.getP_id());
                extras.putString("name",lm.getP_name());
                extras.putString("address",lm.getP_address());
                extras.putString("fees",lm.getReservation_fees());
                extras.putString("lat",lm.getP_lat());
                extras.putString("long",lm.getP_long());
                in.putExtras(extras);
                startActivity(in);
            }
        });
    }

    public class LocationFetch extends AsyncTask<Void,Void,String> {
        Context context;
        ProgressDialog progress;

        public LocationFetch(Context context)
        {
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... voids) {

            String location_url = "http://139.59.18.185/getlocation.php";
            String result = "";

            try {
                URL url = new URL(location_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();
            String[] eachline = result.split("<br>");
            Log.e("Length is ",""+eachline.length);
            for (String l:eachline) {
                Log.e("Line is ",""+l);
                String[] st = l.split("-");
                Log.e("Sub-length is ",""+st.length);
                list.add(new ListModel(st[0],st[1],st[2],st[3],st[4],st[5],st[6]));
            }
            ReservationAdapter adapter = new ReservationAdapter(context, list);
            listView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Fetching locations...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
        }
    }

}

