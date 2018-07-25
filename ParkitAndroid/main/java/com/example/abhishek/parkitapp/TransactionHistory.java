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

public class TransactionHistory extends AppCompatActivity {

    ListView listView;
    SharedPreferences sharedPreferences;
    ArrayList<TransactionModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customactionbar_transaction);

        list = new ArrayList<>();
        sharedPreferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        listView = findViewById(R.id.listViewHistory);

        TransactionFetch transactionFetch = new TransactionFetch(TransactionHistory.this);
        transactionFetch.execute();

    }
    public class TransactionFetch extends AsyncTask<Void,Void,String> {
        Context context;
        ProgressDialog progress;

        public TransactionFetch(Context context)
        {
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... voids) {

            String transaction_url = "http://139.59.18.185/transaction_history.php";
            String result = "";

            try {
                URL url = new URL(transaction_url);
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
                Log.e("Internal Records : ",st.length+"");
                list.add(new TransactionModel(st[0],st[1],st[2],st[3]));
            }
            TransactionAdapter adapter = new TransactionAdapter(context, list);
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
