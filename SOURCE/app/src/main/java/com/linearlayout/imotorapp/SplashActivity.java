package com.linearlayout.imotorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                screenRouter();
            }
        }, 2000);
    }


    void screenRouter()
    {

        SharedPreferences sharedPreferences = getSharedPreferences("ImotorSource",MODE_PRIVATE);
        String phoneTest = sharedPreferences.getString("phoneNumber",null);

        if(phoneTest==null)
        {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
