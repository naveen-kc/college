package com.example.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        recyclerView = findViewById(R.id.recyclerAppoints);
        list = new ArrayList<>();
        getExplore();
    }


    public void getExplore(){
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
}