package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    String type;
    TextView header;
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        header=findViewById(R.id.header);
        username =findViewById(R.id.idEdtUserName);
        password=findViewById(R.id.idEdtPassword);
        login=findViewById(R.id.login);


        type = getIntent().getStringExtra("type");
        if (type.equals("faculty")) {
            header.setText("Faculty Login");
        } else if (type.equals("office")) {
            header.setText("Office Login");
        } else if (type.equals("hostel")) {
            header.setText("Hostel Login");
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLogin();
            }
        });
    }

    private void adminLogin() {

        if(username.getText().toString().equals("")||password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Enter Credentials",Toast.LENGTH_SHORT).show();
        }
        else if(username.getText().toString().equals("faculty")&&password.getText().toString().equals("f123")&&type.equals("faculty")){
            Intent i = new Intent(getApplicationContext(),AdminHome.class);
            i.putExtra("admin","faculty");
            startActivity(i);
            username.setText("");
            password.setText("");
        }
        else if(username.getText().toString().equals("office")||password.getText().toString().equals("o123")&&type.equals("office")){
            Intent i = new Intent(getApplicationContext(),AdminHome.class);
            i.putExtra("admin","office");
            startActivity(i);
            username.setText("");
            password.setText("");
        }
        else if(username.getText().toString().equals("hostel")||password.getText().toString().equals("h123")&&type.equals("hostel")){
            Intent i = new Intent(getApplicationContext(),AdminHome.class);
            i.putExtra("admin","hostel");
            startActivity(i);
            username.setText("");
            password.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(),"Please select proper department or check your credentials",Toast.LENGTH_SHORT).show();
        }
    }






}