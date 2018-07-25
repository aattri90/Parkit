package com.example.abhishek.parkitapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Checkin extends AppCompatActivity {
    private TextView tv,tv1;
    String str1,str2;
    Button b;
    Integer i;
    Bundle extras;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customactionbarcheckin);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv = findViewById(R.id.check_in_tv);
        tv1 = findViewById(R.id.timelimit);
        b = findViewById(R.id.buttonTime);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int ampm = cal.get(Calendar.AM_PM);

//        tv.setText("Date : "+day+"-"+month+"-"+year+"   Time : "+hour+":"+min+" "+am_pm);
        str1 = "Date : "+day+"-"+month+"-"+year;
        if(min<10)
            str2 = "Time : "+hour+":"+"0"+min;
        else
            str2 = "Time : "+hour+":"+min;
        tv.setText(str1+"   "+str2);

        //////////////////////
        String currentDateTime = DateFormat.getDateTimeInstance().format(new Date()).trim();
        String[] st = currentDateTime.split(" ");
        String currentDateTimeString = st[3].trim();
        final String[] ar = currentDateTimeString.split(":");
        i = Integer.parseInt(ar[0]);
        if(st[4].equals("PM") && i!=12)
            i = i + 12;
        if(st[4].equals("PM") && i==12)
            i = i + 1;
        if(st[4].equals("AM") && i!=12)
            i = i+1;
        if(st[4].equals("AM") && i==12)
            i = 1;

        tv1.setText("Reservation is possible upto "+i+":"+ar[1]);

        //////////////////////
        final DatePicker mDatePicker = (DatePicker) findViewById(R.id.check_in_dp);
        mDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                int m = month+1;
                str1 = "Date : "+day+"-"+m+"-"+year;
                tv.setText(str1+"  "+str2);
//                tv.setText("Date : "+day+"-"+m+"-"+year);
            }
        });

        final TimePicker mTimePicker = findViewById(R.id.check_in_tp);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                if(min<10)
                    str2 = "Time : "+hour+":"+"0"+min;
                else
                    str2 = "Time : "+hour+":"+min;
                tv.setText(str1+"   "+str2);
//                tv.setText("Time : "+hour+":"+min);
            }
        });

        extras = getIntent().getExtras();
//        Toast.makeText(Checkin.this,extras.getString("address"),Toast.LENGTH_SHORT).show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int hour = mTimePicker.getHour();
//                int minute = mTimePicker.getMinute();
//                if(hour<=i && hour>=i-1)
//                {
//                    if( (hour==i && minute<Integer.valueOf(ar[1])) || (hour==(i-1) && minute>=Integer.valueOf(ar[1])) )
//                    {
                        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        Intent in = new Intent(Checkin.this,Maps.class);
                        extras.putString("time",timeStamp);
                        in.putExtras(extras);
                        startActivity(in);
                        return;
//                    }
//                }

//                tv1.startAnimation(shakeError());
            }
        });
    }
    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 11, 0, 0);
        shake.setDuration(300);
        shake.setInterpolator(new CycleInterpolator(5));
        return shake;
    }
}
