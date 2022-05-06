package com.example.college;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointsAdapter extends RecyclerView.Adapter<AppointsAdapter.ViewHolder>{



    public List<AppointList> profileDataList;
    public Activity activity;


    public AppointsAdapter(Activity activity, ArrayList<AppointList> profileDataList) {
        this.activity = activity;
        this.profileDataList = profileDataList;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public TextView textview2;
        public TextView textview3;
        public TextView textview4;
        public TextView textview5,status;
        public View maincardd;
        Button accept,reject;
        LinearLayout buttons;



        public ViewHolder(View v){

            super(v);
            textView = (TextView)v.findViewById(R.id.fName);
            textview2=(TextView) v.findViewById(R.id.lName);
            textview3=(TextView) v.findViewById(R.id.purpose);
            textview4=(TextView)v.findViewById(R.id.date);
            textview5=(TextView)v.findViewById(R.id.whom);
            status=(TextView)v.findViewById(R.id.status);
            accept=(Button)v.findViewById(R.id.accept);
            reject=(Button)v.findViewById(R.id.reject);
            buttons=(LinearLayout)v.findViewById(R.id.buttons);

            maincardd=v.findViewById(R.id.cardview);
        }
    }

    @Override
    public AppointsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(activity).inflate(R.layout.appoints_layout,parent,false);

        AppointsAdapter.ViewHolder viewHolder1 = new AppointsAdapter.ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final AppointList profileData = profileDataList.get(position);

        holder.textView.setText(profileDataList.get(position).getFname() + "");
        holder.textview2.setText(profileDataList.get(position).getLname() + "");
        holder.textview3.setText(profileDataList.get(position).getWhom() + "");
        holder.textview4.setText(profileDataList.get(position).getPurpose() + "");
        holder.textview5.setText(profileDataList.get(position).getDate() + "");

        if(profileDataList.get(position).getStatus().equals("1")){
            holder.buttons.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
            holder.status.setText("This appoint has been accepted");
            holder.status.setTextColor(Color.GREEN);
        }
        else if(profileDataList.get(position).getStatus().equals("2")){
            holder.buttons.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
            holder.status.setText("This appoint has been rejected");
            holder.status.setTextColor(Color.RED);
        }
        else {
            holder.buttons.setVisibility(View.VISIBLE);
            holder.status.setVisibility(View.GONE);
        }



        holder.accept.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String url = Const.URL+"Accept";
                                                    RequestQueue requestQueue = Volley.newRequestQueue(activity);
                                                    HashMap<String, String> postData = new HashMap<String, String>();
                                                    postData.put("id",profileDataList.get(position).getId());


                                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url,new JSONObject(postData), new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {

                                                            try {
                                                                JSONObject json = new JSONObject(response.toString());
                                                                Log.i("Response",response.toString());
                                                                String message = json.getString("message");

                                                            Log.i("Response",response.toString());

                                                            new AlertDialog.Builder(activity)
                                                                    .setTitle("Accepted")
                                                                    .setMessage(message)
                                                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int which) {


                                                                            if (activity instanceof AdminHome) {
                                                                                ((AdminHome)activity).getAppointments();
                                                                            }
                                                                            dialog.dismiss();
                                                                            // Continue with delete operation
                                                                        }
                                                                    })
                                                                    // A null listener allows the button to dismiss the dialog and take no further action.
                                                                    //.setNegativeButton(android.R.string.no, null)
                                                                    .setIcon(activity.getResources().getDrawable(R.drawable.ic_baseline_done_all_24))
                                                                    .setCancelable(false)
                                                                    .show();

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
        );

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = Const.URL+"Reject";
                RequestQueue requestQueue = Volley.newRequestQueue(activity);
                HashMap<String, String> postData = new HashMap<String, String>();
                postData.put("id",profileDataList.get(position).getId());
                // postData.put("Password", password.getText().toString());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url,new JSONObject(postData), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Response",response.toString());

                        try {
                            JSONObject json = new JSONObject(response.toString());
                            Log.i("Response",response.toString());
                            String message = json.getString("message");

                            Log.i("Response",response.toString());

                            new AlertDialog.Builder(activity)
                                    .setTitle("Rejected")
                                    .setMessage(message)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (activity instanceof AdminHome) {
                                                ((AdminHome)activity).getAppointments();
                                            }
                                            dialog.dismiss();
                                            // Continue with delete operation
                                        }
                                    })
                                    // A null listener allows the button to dismiss the dialog and take no further action.
                                    //.setNegativeButton(android.R.string.no, null)
                                    .setIcon(activity.getResources().getDrawable(R.drawable.ic_baseline_done_all_24))
                                    .setCancelable(false)
                                    .show();

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
        });

    }


    @Override
    public int getItemCount(){

        return profileDataList.size();
    }



}

