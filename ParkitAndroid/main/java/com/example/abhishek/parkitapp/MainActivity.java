package com.example.abhishek.parkitapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ImageView iv, iv3;
    TextView tv;
    SharedPreferences sharedPreferences;
    static final String[] Park_It = new String[]{"Make a Reservation", "Upcoming Reservations", "Slot Locator", "Transaction History"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.logo_title_bar);

        sharedPreferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        tv = findViewById(R.id.textView2);
        tv.setText("Welcome " + sharedPreferences.getString("name","User"));
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyArrayAdapter(this, Park_It));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = Park_It[position];
                if(s.equals("Make a Reservation"))
                {
                    Intent in = new Intent(MainActivity.this, Reservation.class);
                    startActivity(in);
                }
                else if(s.equals("Upcoming Reservations"))
                {
                    Intent in = new Intent(MainActivity.this, Navigation.class);
                    startActivity(in);
                }
                else if(s.equals("Slot Locator"))
                {
                    SlotLocator slotLocator = new SlotLocator(MainActivity.this);
                    slotLocator.execute("slotLocator");
                }
                else if(s.equals("Transaction History"))
                {
                    Intent in = new Intent(MainActivity.this, TransactionHistory.class);
                    startActivity(in);
                }
            }
        });

        iv3 = findViewById(R.id.imageView3);
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent in = new Intent(getApplicationContext(),Splash.class);
                startActivity(in);
                finishAffinity();
            }
        });

        iv = findViewById(R.id.imageView4);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = sharedPreferences.getString("name", null) + "\n" + sharedPreferences.getString("license", null) +
                        "\n" +sharedPreferences.getString("mobile", null) + "\n" + sharedPreferences.getString("street", null) +
                        "\n" +sharedPreferences.getString("city", null) + "\n" + sharedPreferences.getString("state", null) +
                        "\n" +sharedPreferences.getString("country", null) + "\n" + sharedPreferences.getString("zip", null) +
                        "\n" +sharedPreferences.getString("email", null);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
//                editor.clear();
//                editor.commit();
            }
        });
    }
    public class SlotLocator extends AsyncTask<String, Void, String> {
        Context context;
        ProgressDialog progress;

        SlotLocator(Context context) { this.context = context; }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Fetching information...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String slot_url = "http://139.59.18.185/slot_locator.php";
            String result = "";
            if (params[0].equals("slotLocator")) {
                try {
                    URL url = new URL(slot_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    String post_data = URLEncoder.encode("license", "UTF-8") + "=" + URLEncoder.encode(sharedPreferences.getString("license", null),"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            return "abcd";
        }

        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();
            if(result.equals("No results")) {
                Toast.makeText(context, "No active reservation found!", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] sarray = result.trim().split("-");
            String s = "Parking: "+sarray[0]+",\n\t\t\t\t\t\t\t\t "+sarray[1]+"\n"+"Floor: "+sarray[4]+"\n"+"Slot: "+sarray[5]+"\n"+
                         "Vehicle no.: "+sarray[2]+"\n"+"Vehicle type: "+sarray[3]+" wheeler";
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
        }
    }
}