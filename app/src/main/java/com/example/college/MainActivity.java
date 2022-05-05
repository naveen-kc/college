package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText userId,password ;
    Button signIn,visitor;
    StorageHelper storageHelper;
    TextView create;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storageHelper = new StorageHelper(getApplicationContext());
        userId=findViewById(R.id.idEdtUserName);
        password = findViewById(R.id.idEdtPassword);
        signIn=findViewById(R.id.idBtnLogin);
        //visitor=findViewById(R.id.idVisitor);
        create =findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });





        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Credentials",Toast.LENGTH_SHORT).show();
                }/*else if(userId.getText().toString().equals("user") || password.getText().toString().equals("user123")) {

                    Intent i = new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                }*/
                else {
                    callLoginApi();
                    //Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    void callLoginApi(){

        String url = Const.URL+"Login";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        HashMap<String, String> postData = new HashMap<String, String>();
            postData.put("Contact",userId.getText().toString());
           // postData.put("Password", password.getText().toString());



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(postData), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());

                    JSONArray jsonArray = (JSONArray) json.get("result");

                    Log.i("Response",jsonArray.toString());

                    for (int i = 0, len = jsonArray.length(); i < len; i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        pass = data.getString("password");
                        Log.i("Response",pass+password.getText().toString());

                        if(pass.equals(password.getText().toString())){

                            storageHelper.putUserFName(data.getString("First_Name"));
                            storageHelper.putUserLName(data.getString("Last_Name"));
                            storageHelper.putUserNumber(data.getString("Contact"));
                            storageHelper.putUserAge(data.getString("Age"));
                            storageHelper.putPassword(data.getString("password"));
                            Intent intent = new Intent(MainActivity.this,Home.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Incorrect password",Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response","errorrrr :"+error);
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        }




    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}