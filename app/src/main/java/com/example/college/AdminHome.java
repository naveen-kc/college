package com.example.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminHome extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AppointList> list;
    AppointsAdapter adapter;
    JSONArray users;
    String admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        admin = getIntent().getStringExtra("admin");
        recyclerView = findViewById(R.id.recyclerAppoints);
        list = new ArrayList<>();
        getAppointments();
    }


    public void getAppointments(){
        findViewById(R.id.loadingPanell).setVisibility(View.VISIBLE);
        String Url =Const.URL+"appointments";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("Response",response.toString());

                        try {


                            list = new ArrayList<>();
                          //  JSONArray data = response.toString();
                           // JSONObject  jsonRootObject = new JSONObject(data);
                           // JSONArray jsonArray = jsonRootObject.optJSONArray("result");
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);

                                if(jo.optString("Department").equals(admin)){

                                list.add(new AppointList(
                                        jo.optString("Personid"),
                                        jo.optString("First_Name"),
                                        jo.optString("Last_Name"),
                                        jo.optString("Age"),
                                        jo.optString("Purpose"),
                                        jo.optString("statusCode"),
                                        jo.optString("WHO_ARE_YOU"),
                                        jo.optString("WHOM_TO_VISIT"),
                                        jo.optString("WHEN_TO_VISIT"),
                                        jo.optString("Department"),
                                        jo.optString("Contact")
                                ));

                                }
                            }

                            adapter = new AppointsAdapter(AdminHome.this,list);
                            recyclerView.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(AdminHome.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                            findViewById(R.id.loadingPanell).setVisibility(View.GONE);
                        }
                        catch (Exception e) {
                            Log.i("Response",e.toString());
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Response",error.toString());
                        Toast.makeText(AdminHome.this,"Server Down,Please wait",Toast.LENGTH_SHORT).show();
                    }


                }) {


        };
        RequestQueue requestQueue = Volley.newRequestQueue(AdminHome.this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(AdminHome.this)
                .setTitle("Exit..?")
                .setMessage("Want to exit form admin panel.?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),SelectDept.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        dialog.dismiss();
                        // Continue with delete operation
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(getResources().getDrawable(R.drawable.ic_baseline_warning_24))
                .setCancelable(false)
                .show();
    }
}