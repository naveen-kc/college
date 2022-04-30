package com.example.college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import java.io.*;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);

    }
    VisitFragment visitFragment = new VisitFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    BookFragment bookFragment = new BookFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {


            case R.id.visit:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, visitFragment).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                return true;

            case R.id.book:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, bookFragment).commit();
                return true;


        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}