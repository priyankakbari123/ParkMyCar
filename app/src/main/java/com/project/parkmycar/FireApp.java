package com.project.parkmycar;

import android.app.Application;

import androidx.annotation.NonNull;

import com.firebase.client.Firebase;

public class FireApp extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
