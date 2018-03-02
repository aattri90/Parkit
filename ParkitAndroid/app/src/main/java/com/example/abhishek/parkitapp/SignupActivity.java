package com.example.abhishek.parkitapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText name,license,street,city,state,country,zip,mobile,email,password,confirm_pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customactionbar_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.editText_signup1);
        license = findViewById(R.id.editText_signup2);
        street = findViewById(R.id.editText_signup3);
        city = findViewById(R.id.editText_signup4);
        state = findViewById(R.id.editText_signup5);
        country = findViewById(R.id.editText_signup6);
        zip = findViewById(R.id.editText_signup10);
        mobile = findViewById(R.id.editText_signup11);
        email = findViewById(R.id.editText_signup7);
        password = findViewById(R.id.editText_signup8);
        confirm_pass = findViewById(R.id.editText_signup9);

        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(name.getText().toString().matches(".*\\d+.*"))
                {
                    Toast.makeText(SignupActivity.this, "Name cannot contain numerals!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(license.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your license no.!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(street.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your street!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(city.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your city!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(city.getText().toString().matches(".*\\d+.*"))
                {
                    Toast.makeText(SignupActivity.this, "Please check your city!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(state.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your state!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(state.getText().toString().matches(".*\\d+.*"))
                {
                    Toast.makeText(SignupActivity.this, "Please check your state!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(country.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your country!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(country.getText().toString().matches(".*\\d+.*"))
                {
                    Toast.makeText(SignupActivity.this, "Please check your country!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(zip.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your zip code!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(zip.getText().toString().length()!=6)
                {
                    Toast.makeText(SignupActivity.this, "Zip code should contain 6 digits!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mobile.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your mobile no.!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mobile.getText().toString().length()!=10)
                {
                    Toast.makeText(SignupActivity.this, "Mobile should contain 10 digits!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your email id!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!email.getText().toString().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))
                {
                    Toast.makeText(SignupActivity.this, "Please check your email id!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirm_pass.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Please re-enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.getText().toString().equals(confirm_pass.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Passwords donot match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().length() < 6)
                {
                    Toast.makeText(SignupActivity.this, "Password should contain atleast 6 characters!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(name.getText().toString(),license.getText().toString(),
                                     street.getText().toString(),city.getText().toString(),state.getText().toString(),
                                     country.getText().toString(),zip.getText().toString(),
                                     mobile.getText().toString(),email.getText().toString(),password.getText().toString());
                BackgroundWorker bw = new BackgroundWorker(SignupActivity.this,user,"signup");
                bw.execute();
//                Intent in = new Intent(getBaseContext(),LoginActivity.class);
//                startActivity(in);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
