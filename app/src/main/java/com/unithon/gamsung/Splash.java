package com.unithon.gamsung;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by kyun on 2016-07-30.
 */
public class Splash extends Activity {

    ProgressDialog dialog;
    boolean check = true;
    Context context;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                dialog = ProgressDialog.show(Splash.this, "",
                        "로딩중입니다", true);
                Intent intent = new Intent(Splash.this, LoginActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
