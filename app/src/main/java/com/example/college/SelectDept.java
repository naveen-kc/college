package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectDept extends AppCompatActivity {
    Button faculty,office,hostel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dept);
        hostel =findViewById(R.id.hostel);
        office=findViewById(R.id.office);
        faculty=findViewById(R.id.faculty);


        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminLogin.class);
                i.putExtra("type","faculty");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminLogin.class);
                i.putExtra("type","office");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });

        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminLogin.class);
                i.putExtra("type","hostel");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });

    }
}