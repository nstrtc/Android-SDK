package com.nstrtcdemo;

import android.app.Application;


import com.vcrtc.webrtc.RTCManager;

import java.util.UUID;

public class NSTApplication extends Application {

    public String checkDup = UUID.randomUUID().toString();
    @Override
    public void onCreate() {
        super.onCreate();

        RTCManager.init(this);
    }
}
