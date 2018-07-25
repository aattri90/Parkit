package com.example.abhishek.parkitapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheckActivity extends Activity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        sharedPreferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        if(!sharedPreferences.contains("name"))
        {
            Intent in = new Intent(this, Splash.class);
            startActivity(in);
            finish();
        }
        else{
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
            finish();
        }
    }
}
