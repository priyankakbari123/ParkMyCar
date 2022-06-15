package com.project.parkmycar;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    Context context;
    SharedPreferences sharedPreferences;
    private  String email;
    public void removeUser(){
        sharedPreferences.edit().clear().commit();
    }
    public String getEmail(){
        email=sharedPreferences.getString("userdata","");
        return email;
    }
    public void setEmail(String email){
        this.email=email;
        sharedPreferences.edit().putString("userdata",email).commit();
    }
    public User(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("userinfo",context.MODE_PRIVATE);
    }
}
