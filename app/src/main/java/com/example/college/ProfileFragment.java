package com.example.college;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    TextView name,mobile,age;
    Context context;
    StorageHelper storageHelper;
    Button logout;
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
        logout=rootView.findViewById(R.id.logout);


        Log.i("Name",storageHelper.getUserFName());
        name.setText(storageHelper.getUserFName());
        mobile.setText(storageHelper.getUserNumber());
        age.setText(storageHelper.getUserAge());



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Logout..?")
                        .setMessage("Do you really want to logout.?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                storageHelper.putUserNumber("");
                                storageHelper.putPassword("");
                                storageHelper.putUserAge("");
                                storageHelper.putUserLName("");
                                storageHelper.putUserFName("");
                                Intent i = new Intent(context,MainActivity.class);
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
        });

        return rootView;
    }
}