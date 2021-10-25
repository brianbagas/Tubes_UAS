package com.example.tubes_uts_e_2;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationFirebase extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("sample_notification");
    }
}