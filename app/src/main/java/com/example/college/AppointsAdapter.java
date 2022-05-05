package com.example.college;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        public TextView textview5;
        public View maincardd;



        public ViewHolder(View v){

            super(v);
            textView = (TextView)v.findViewById(R.id.fName);
            textview2=(TextView) v.findViewById(R.id.lName);
            textview3=(TextView) v.findViewById(R.id.purpose);
            textview4=(TextView)v.findViewById(R.id.date);
            textview5=(TextView)v.findViewById(R.id.whom);
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
    public void onBindViewHolder(@NonNull AppointsAdapter.ViewHolder holder, int position) {
        final AppointList profileData = profileDataList.get(position);

        holder.textView.setText(profileDataList.get(position).getFname() + "");
        holder.textview2.setText(profileDataList.get(position).getLname() + "");
        holder.textview3.setText(profileDataList.get(position).getWhom() + "");
        holder.textview4.setText(profileDataList.get(position).getPurpose() + "");
        holder.textview5.setText(profileDataList.get(position).getDate() + "");



        holder.maincardd.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {



                                                }

                                            }
        );

    }


    @Override
    public int getItemCount(){

        return profileDataList.size();
    }



}

