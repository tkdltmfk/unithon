package com.unithon.gamsung;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.naver.speech.clientapi.SpeechConfig;
import com.naver.speech.clientapi.SpeechRecognitionException;
import com.naver.speech.clientapi.SpeechRecognitionListener;
import com.naver.speech.clientapi.SpeechRecognizer;

public class VoiceRecognizer implements SpeechRecognitionListener {

    static final int onInactive = 0;
    static final int onReady = 1;
    static final int onRecord = 2;
    static final int onPartitialResult = 3;
    static final int onEndPointDetected = 4;
    static final int onResult = 5;
    static final int onError = 6;
    Handler handler;
    SpeechRecognizer voiceRecognizer;

    public VoiceRecognizer(Activity activity, Handler handler, String clientId, SpeechConfig speechConfig) {
        this.handler = handler;

        try {
            voiceRecognizer = new SpeechRecognizer(activity, clientId, speechConfig);
        } catch (SpeechRecognitionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        voiceRecognizer.setSpeechRecognitionListener(this);
    }

    public SpeechRecognizer getSpeechRecognizer() {
        return voiceRecognizer;
    }

    public void recognize() {
        try {
            voiceRecognizer.recognize();
        } catch (SpeechRecognitionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onEndPointDetected() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onError(int arg0) {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = onError;
        msg.obj = arg0;
        handler.sendMessage(msg);
    }

    @Override
    public void onInactive() {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = onInactive;
        handler.sendMessage(msg);
    }

    @Override
    public void onPartitialResult(String arg0) {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = onPartitialResult;
        msg.obj = arg0;
        handler.sendMessage(msg);
    }

    @Override
    public void onReady() {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = onReady;
        handler.sendMessage(msg);
    }

    @Override
    public void onRecord(short[] arg0) {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = onRecord;
        msg.obj = arg0;
        handler.sendMessage(msg);
    }

    @Override
    public void onResult(Object[] arg0) {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = onResult;
        msg.obj = arg0;
        handler.sendMessage(msg);
    }

}