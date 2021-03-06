package com.nstrtcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.vcrtc.VCRTC;
import com.vcrtc.VCRTCPreferences;
import com.vcrtc.VCRTCView;
import com.vcrtc.callbacks.CallBack;
import com.vcrtc.entities.ResponseCode;
import com.vcrtc.listeners.VCRTCListener;
import com.vcrtc.listeners.VCRTCListenerImpl;

public class NSTVideoActivity extends AppCompatActivity {

    private FrameLayout flLocal, flRemote;

    private VCRTC vcrtc;

    private boolean isEnableAudio, isEnableVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video);

        flLocal = findViewById(R.id.fl_local);
        flRemote = findViewById(R.id.fl_remote);

        VCRTCPreferences prefs = new VCRTCPreferences(this);
        prefs.setSimulcast(false);

        makeCall();
    }

    private void makeCall() {
        vcrtc = new VCRTC(this);
        vcrtc.setVCRTCListener(listener);
        vcrtc.connect("1865", "123456", "王五", new CallBack() {
            @Override
            public void success(String s) {

            }

            @Override
            public void failure(ResponseCode responseCode) {

            }
        });
    }

    public void enableAudio(View view) {
        vcrtc.setAudioEnable(isEnableAudio);
        isEnableAudio = !isEnableAudio;
    }

    public void enableVideo(View view) {
        vcrtc.setVideoEnable(isEnableVideo);
        isEnableVideo = !isEnableVideo;
    }

    public void switchCamera(View view) {
        vcrtc.switchCamera();
    }

    public void disconnect(View view) {
        vcrtc.disconnect();
    }

    private VCRTCListener listener = new VCRTCListenerImpl() {

        @Override
        public void onLocalVideo(String uuid, VCRTCView view) {
            view.setMirror(true);
            flLocal.addView(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        }

        @Override
        public void onRemoteVideo(String uuid, VCRTCView view) {
            flRemote.addView(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        }
    };
}
