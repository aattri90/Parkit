package com.example.abhishek.parkitapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.Date;

import instamojo.library.Config;
import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {
    EditText et;
    Bundle extras;
    Button reserve;
    TextView pname, paddr, pfees;
    String longitude, latitude;
    GoogleMap mgooglemap;
    InstapayListener listener;
    SharedPreferences sharedPreferences;
    SlotBooking slotBooking;
    SlotUnbooking slotUnbooking;
    VEntry vEntry;
    RadioGroup radioGroup;
    RadioButton wheeler;
    ArrayList<String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (googleServicesAvailable()) {
            initmap();
        } else {
        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customactionbarmaps);

        et = findViewById(R.id.v_number);
        extras = getIntent().getExtras();
        pname = findViewById(R.id.textView10);
        paddr = findViewById(R.id.textView11);
        pfees = findViewById(R.id.textView12);
        radioGroup = findViewById(R.id.radioGroup);
        info = new ArrayList<>(3);

        Log.e("extras id = ", extras.get("id").toString());
        sharedPreferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        pname.setText(extras.getString("name"));
        paddr.setText(extras.getString("address"));
        pfees.setText("Rs. " + extras.getString("fees"));

        reserve = findViewById(R.id.button4);
        slotBooking = new SlotBooking(Maps.this);
        slotUnbooking = new SlotUnbooking(Maps.this);
        vEntry = new VEntry(Maps.this);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et.getText().toString())) {
                    Toast.makeText(Maps.this, "Please enter your Vehicle No.!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int selectedId = radioGroup.getCheckedRadioButtonId();
                wheeler = findViewById(selectedId);
                slotBooking.execute("book", wheeler.getText().toString().substring(0, 1));
            }
        });
    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't Connect to play services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void initmap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgooglemap = googleMap;
        extras = getIntent().getExtras();
        latitude = extras.getString("lat");
        longitude = extras.getString("long");
        Double lat = new Double(latitude);
        Double lng = new Double(longitude);
        gotoLocationZoom(lat, lng, (float) 15.0);
        Log.e("Location", "Lat" + lat + " long" + lng);
        MarkerOptions markerOptions = new MarkerOptions().title(extras.getString("name")).position(new LatLng(lat, lng))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mgooglemap.addMarker(markerOptions);
    }

    private void gotoLocationZoom(Double lat, Double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mgooglemap.moveCamera(update);
    }


    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG).show();
                slotUnbooking.execute("cancel");
                vEntry.execute("ventry");
            }
        };
    }

    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("env", Config.PROD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    public class SlotBooking extends AsyncTask<String, Void, String> {
        Context context;
        ProgressDialog progress;
        String wheeler;

        private SlotBooking(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Looking for empty slots...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String booking_url = "http://139.59.18.185/slotbooking.php";
            String result = "";
            wheeler = params[1];

            if (params[0].equals("book")) {
                try {
                    URL url = new URL(booking_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(extras.getString("id"), "UTF-8")
                            + "&" + URLEncoder.encode("wheeler", "UTF-8") + "=" + URLEncoder.encode(wheeler, "UTF-8");
                    Log.e("//////////", extras.getString("id") + " " + wheeler);
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
            return "abcd";
        }

        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();

            if (result.equals("None")) {      //  slots not available
                Toast.makeText(context, "Sorry, No slots available in this parking!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
//            Log.e("result = ","");
            String[] sarray = result.trim().split("-");
            info.add(0, sarray[0]);    //floor
            info.add(1, sarray[1]);    //slot
            info.add(2, wheeler);      //wheeler

            Log.e("first element :", info.get(0) + "");
            Log.e("second element :", info.get(1) + "");

            callInstamojoPay(sharedPreferences.getString("email", null),
                    sharedPreferences.getString("mobile", null),
                    extras.getString("fees"), "Reserve parking slot",
                    sharedPreferences.getString("name", null));
        }
    }

    public class SlotUnbooking extends AsyncTask<String, Void, String> {
        Context context;

        private SlotUnbooking(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {

            String unbooking_url = "http://139.59.18.185/slotunbooking.php";
            String result = "";
            if (params[0].equals("cancel")) {
                try {
                    URL url = new URL(unbooking_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(extras.getString("id"), "UTF-8") + "&"
                            + URLEncoder.encode("wheeler", "UTF-8") + "=" + URLEncoder.encode(info.get(2), "UTF-8") + "&"
                            + URLEncoder.encode("floor", "UTF-8") + "=" + URLEncoder.encode(info.get(0), "UTF-8") + "&"
                            + URLEncoder.encode("slot", "UTF-8") + "=" + URLEncoder.encode(info.get(1), "UTF-8");
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
            if (!result.equals("cancel"))     //  cancel payment
                Log.e("Error deleting :", result);
            else
                Log.e("Deletiion Done ! :", result);
        }
    }

    public class VEntry extends AsyncTask<String, Void, String> {
        Context context;

        private VEntry(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {

            String unbooking_url = "http://139.59.18.185/ventry.php";
            String result = "";
            if (params[0].equals("ventry")) {
                try {
                    URL url = new URL(unbooking_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(extras.getString("id"), "UTF-8") + "&"
                            + URLEncoder.encode("license", "UTF-8") + "=" + URLEncoder.encode(sharedPreferences.getString("license",null), "UTF-8") + "&"
                            + URLEncoder.encode("vehicleno", "UTF-8") + "=" + URLEncoder.encode(et.getText().toString(), "UTF-8") + "&"
                            + URLEncoder.encode("wheeler", "UTF-8") + "=" + URLEncoder.encode(info.get(2), "UTF-8") + "&"
                            + URLEncoder.encode("slot", "UTF-8") + "=" + URLEncoder.encode(info.get(1), "UTF-8") + "&"
                            + URLEncoder.encode("doe", "UTF-8") + "=" + URLEncoder.encode(timeStamp, "UTF-8") + "&"
                            + URLEncoder.encode("floor", "UTF-8") + "=" + URLEncoder.encode(info.get(0), "UTF-8") + "&"
                            + URLEncoder.encode("vstatus", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(0), "UTF-8");
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
                Log.e("VEntry Result: ", result);
        }
    }
}
