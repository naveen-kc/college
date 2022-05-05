package com.example.college;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.ViewHolder>{
    public ArrayList<VisitList> profileDataList;
    public Context activity;


    public VisitAdapter(Context activity, ArrayList<VisitList> profileDataList) {
        this.activity = activity;
        this.profileDataList = profileDataList;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView textView,textview2,textview3,textview4,textview5,textview6;
        public View maincardd;
        // public TextView idd;
        public Button Call;


        public ViewHolder(View v){

            super(v);
            //image=itemView.findViewById(R.id.mainimage);
            textView = (TextView)v.findViewById(R.id.Name);
            textview2 = (TextView)v.findViewById(R.id.Lname);
            textview3 = (TextView)v.findViewById(R.id.purpose);
            textview4 = (TextView)v.findViewById(R.id.date);
            textview5 = (TextView)v.findViewById(R.id.whom);
            textview6 = (TextView)v.findViewById(R.id.status);
            //textview2 = (TextView)v.findViewById(R.id.Lname);



            maincardd=(CardView)v.findViewById(R.id.cardview);
            // idd = (TextView)v.findViewById(R.id.id);


        }
    }

    @Override
    public VisitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(activity).inflate(R.layout.visits_layout,parent,false);

        VisitAdapter.ViewHolder viewHolder1 = new VisitAdapter.ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitAdapter.ViewHolder holder, int position) {
        final VisitList profileData = profileDataList.get(position);

        if (profileDataList.get(position).getStatusCode().equals("1")) {
            holder.maincardd.setBackgroundColor(Color.GREEN);
        } else if (profileDataList.get(position).getStatusCode().equals("2")){
            holder.maincardd.setBackgroundColor(Color.GRAY);
      }
        else {
            holder.maincardd.setBackgroundColor(Color.CYAN);
        }

         //Product product = profileDataList.get(position);
        holder.textView.setText("First Name:"+profileDataList.get(position).getName() + "");
        holder.textview2.setText("Last Name:"+profileDataList.get(position).getLname() + "");
        holder.textview3.setText("Purpose :"+profileDataList.get(position).getPuspose() + "");
        holder.textview4.setText("Date:"+profileDataList.get(position).getWhenToVisit() + "");
        holder.textview5.setText("Whom to Meet:"+profileDataList.get(position).getWhomToVisit() + "");

        if(profileDataList.get(position).getStatusCode().equals("1")){
            holder.textview6.setText( "Wow.! Your appointment is Successfully Scheduled");
        }
        else if (profileDataList.get(position).getStatusCode().equals("2")){
            holder.textview6.setText( "Soory.! Your Appointment is Rejected");
        }
        else {
            holder.textview6.setText( "Your Appointment is still in Pending");
        }

        // holder.textview2.setText(profileDataList.get(position).getDept() + "");




       /* Glide.with(activity)
                .load(profileDataList.get(position).getImage())
                .into(holder.image);*/






        // Log.e("multi",profileData.getMultiimages());

        holder.maincardd.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                   /* Intent intent=new Intent(activity,AllDetails.class);
                                                    intent.putExtra("cat_id",profileDataList.get(position).getId());
                                                    intent.putExtra("cat_name",profileDataList.get(position).getCat());
                                                    intent.putExtra("cat_image",profileDataList.get(position).getImage());
                                                    activity.startActivity(intent);*/



                                                }

                                            }
        );


    }






    @Override
    public int getItemCount(){

        return profileDataList.size();
    }

}