package com.example.abhishek.parkitapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
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
import java.util.ArrayList;

public class Navigation extends AppCompatActivity {

    ListView listView;
    SharedPreferences sharedPreferences;
    ArrayList<NavigationModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customactionbar_navigation);

        list = new ArrayList<>();
        sharedPreferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        listView = findViewById(R.id.listNavigate);

        NavigationFetch navigationFetch= new NavigationFetch(Navigation.this);
        navigationFetch.execute();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavigationModel nm = list.get(position);
//                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", nm.getLatitude(), nm.getLongitude(), "Where the party is at");
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                intent.setPackage("com.google.android.apps.maps");
//                startActivity(intent);
//            }
//        });

    }
    public class NavigationFetch extends AsyncTask<Void,Void,String> {
        Context context;
        ProgressDialog progress;

        public NavigationFetch(Context context)
        {
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... voids) {

            String navigation_url = "http://139.59.18.185/navigation.php";
            String result = "";

            try {
                URL url = new URL(navigation_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("license", "UTF-8") + "=" + URLEncoder.encode(sharedPreferences.getString("license",null));
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
                Log.e("result do in bg : ", result);
                httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();
            if(result.equals("No results")) {
                Toast.makeText(context, "No records found!", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] eachline = result.split("<br>");
            Log.e("Records present: ",eachline.length+"");
            Toast.makeText(context,eachline.length+" records found",Toast.LENGTH_SHORT).show();
            for (String l:eachline) {
                String[] st = l.split("_");
                Log.e("Date is +++: ",l);
                list.add(new NavigationModel(st[0],st[1],st[2],st[3]));
            }
            NavigationAdapter adapter = new NavigationAdapter(context, list);
            listView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Fetching your transactions...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
        }
    }

}
