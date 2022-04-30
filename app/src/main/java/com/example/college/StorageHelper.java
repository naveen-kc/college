package com.example.college;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageHelper {
    private SharedPreferences app_prefs;
    private Context context;
    public StorageHelper(Context context){
        app_prefs = context.getSharedPreferences("application",Context.MODE_PRIVATE);
        this.context = context;
    }


    public void putUserFName(String name){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString("name", name);
        edit.apply();
        edit.commit();
    }

    public String getUserFName(){
        return app_prefs.getString("name","");
    }



    public void putUserLName(String name){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString("lname", name);
        edit.apply();
        edit.commit();
    }

    public String getUserLName(){
        return app_prefs.getString("lname","");
    }

    public void putUserNumber(String name){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString("number", name);
        edit.apply();
        edit.commit();
    }

    public String getUserNumber(){
        return app_prefs.getString("number","");
    }


    public void putUserAge(String name){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString("age", name);
        edit.apply();
        edit.commit();
    }

    public String getUserAge(){
        return app_prefs.getString("age","");
    }


    public void putPassword(String name){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString("password", name);
        edit.apply();
        edit.commit();
    }

    public String getPassword(){
        return app_prefs.getString("password","");
    }
}
