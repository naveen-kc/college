package com.example.college;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    TextView name,mobile,age;
    Context context;
    StorageHelper storageHelper;
    public ProfileFragment(){

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        context = rootView.getContext();
        storageHelper = new StorageHelper(context);

        name =rootView.findViewById(R.id.userName);
        mobile =rootView.findViewById(R.id.userMobile);
        age=rootView.findViewById(R.id.userAge);


        Log.i("Name",storageHelper.getUserFName());
        name.setText(storageHelper.getUserFName());
        mobile.setText(storageHelper.getUserNumber());
        age.setText(storageHelper.getUserAge());





        return rootView;
    }
}