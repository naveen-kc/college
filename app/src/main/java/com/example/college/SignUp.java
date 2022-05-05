package com.example.college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    EditText name,lname,password,age,mobile,confirm;
    StorageHelper storageHelper;
    LottieAnimationView loader;
    SharedPreferences app_prefs;

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //loader = findViewById(R.id.progressBar);
        app_prefs =  getApplicationContext().getSharedPreferences("application", Context.MODE_PRIVATE);
        signUp =findViewById(R.id.idBtnSignup);
        storageHelper = new StorageHelper(getApplicationContext());
        name = (EditText) findViewById(R.id.idEdtName);
        lname =(EditText)  findViewById(R.id.idLname);
        password = (EditText)  findViewById(R.id.idEdtPassword);
        age =(EditText) findViewById(R.id.idEdtAge);
        mobile = (EditText) findViewById(R.id.idEdtMobile);
        confirm=(EditText) findViewById(R.id.idEdtConfirm);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    public  void signUp(){
        if(name.getText().toString().isEmpty()||lname.getText().toString().isEmpty()||password.getText().toString().isEmpty()||age.getText().toString().isEmpty()||mobile.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all the forms",Toast.LENGTH_SHORT).show();

        }
        else  if(!password.getText().toString().equals(confirm.getText().toString())){
            Log.i("Response",password.getText().toString() +confirm.getText().toString());
            Toast.makeText(getApplicationContext(),"Confirm password not matching.!",Toast.LENGTH_SHORT).show();

        }
        else {


            String url = Const.URL+"createProfile";
            RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);

            HashMap<String, String> postData = new HashMap<String, String>();
            postData.put("LastName",lname.getText().toString());
            postData.put("FirstName", name.getText().toString());
            postData.put("Age", age.getText().toString());
            postData.put("Contact", mobile.getText().toString());
            postData.put("password", password.getText().toString());


            Log.i("Response",password.getText().toString());



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(postData), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONObject json = new JSONObject(response.toString());
                        Log.i("Response",response.toString());
                        String message = json.getString("message");



                        Log.i("Response",json.toString() + message);


                          //  Log.i("Response",data.toString());

                            if(message.equals("successfully Created Profile")){
                                storageHelper.putUserFName(name.getText().toString());
                                storageHelper.putUserLName(lname.getText().toString());
                                storageHelper.putUserAge(age.getText().toString());
                                storageHelper.putUserNumber(mobile.getText().toString());
                                storageHelper.putPassword(password.getText().toString());
                                Intent intent = new Intent(getApplicationContext(),Home.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Try after sometimes",Toast.LENGTH_SHORT).show();
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
    }
}