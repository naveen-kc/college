package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    StorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        storageHelper = new StorageHelper(getApplicationContext());


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
               checkSession();
            }
        }, 1000);
    }


    void checkSession(){
        if(storageHelper.getUserNumber().isEmpty()){
            Log.i("Number",storageHelper.getUserNumber().toString());
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);
        }
    }
}