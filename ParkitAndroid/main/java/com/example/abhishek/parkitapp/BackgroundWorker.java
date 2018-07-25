package com.example.abhishek.parkitapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    User user;
    String type;
    ProgressDialog progress;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    BackgroundWorker(Context ctx){
        context = ctx;
    }
    BackgroundWorker(Context context, User user, String type)
    {
        this.context = context;
        this.user = user;
        this.type = type;
    }

    public BackgroundWorker(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    protected String  doInBackground(String... params) {
//        String type = params[0];
        String login_url = "http://139.59.18.185/login.php";
        String signup_url = "http://139.59.18.185/register.php";

        if(type.equals("login"))
        {
            try {
                String user_name = params[0];
                String password = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(type.equals("signup"))
        {
            try {
                String name = user.getName();
                String license = user.getLicense();
                String street = user.getStreet();
                String city = user.getCity();
                String state = user.getState();
                String country = user.getCountry();
                String zip = user.getZip();
                String mobile = user.getMobile();
                String email = user.getEmail();
                String password = user.getPassword();

                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("license","UTF-8")+"="+URLEncoder.encode(license,"UTF-8")+"&"
                        +URLEncoder.encode("street","UTF-8")+"="+URLEncoder.encode(street,"UTF-8")+"&"
                        +URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city,"UTF-8")+"&"
                        +URLEncoder.encode("state","UTF-8")+"="+URLEncoder.encode(state,"UTF-8")+"&"
                        +URLEncoder.encode("country","UTF-8")+"="+URLEncoder.encode(country,"UTF-8")+"&"
                        +URLEncoder.encode("zip","UTF-8")+"="+URLEncoder.encode(zip,"UTF-8")+"&"
                        +URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                sharedPreferences = context.getSharedPreferences("MyPreference",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("license",license);
                editor.putString("mobile",mobile);
                editor.putString("street",street);
                editor.putString("city",city);
                editor.putString("state",state);
                editor.putString("country",country);
                editor.putString("zip",zip);
                editor.putString("email",email);
                editor.commit();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        if(type.equals("signup"))
            progress.setMessage("Signing up...");
        else if(type.equals("login"))
            progress.setMessage("Logging you in...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

    }


    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();

        if(result.contains("SignupSuccess")) {           //   Signup
            Intent in = new Intent(context, MainActivity.class);
            context.startActivity(in);
            String[] sarray = result.trim().split(" ");
            ((Activity) context).finishAffinity();
            Toast.makeText(context,"Welcome "+sarray[1],Toast.LENGTH_LONG).show();
        }
        else if(result.contains("LoginSuccess"))              //    Login
        {
            String[] sarray = result.trim().split("<br>");
            sharedPreferences = context.getSharedPreferences("MyPreference",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",sarray[1]);
            editor.putString("license",sarray[2]);
            editor.putString("mobile",sarray[3]);
            editor.putString("street",sarray[4]);
            editor.putString("city",sarray[5]);
            editor.putString("state",sarray[6]);
            editor.putString("country",sarray[7]);
            editor.putString("zip",sarray[8]);
            editor.putString("email",sarray[9]);
            editor.commit();
            Intent in = new Intent(context, MainActivity.class);
            context.startActivity(in);
            ((Activity) context).finishAffinity();
            Toast.makeText(context,"Welcome "+sarray[1],Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
