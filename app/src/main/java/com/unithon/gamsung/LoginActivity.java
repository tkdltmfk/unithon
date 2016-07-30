package com.unithon.gamsung;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kyun on 2016-07-30.
 */
public class LoginActivity extends Activity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText id, pw;
    Button Login, Signup;
    CheckBox Autologin;
    private boolean login_check = true;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id = (EditText) findViewById(R.id.emailInput);
        pw = (EditText) findViewById(R.id.passwordInput);
        Login = (Button) findViewById(R.id.loginButton);
        Signup = (Button) findViewById(R.id.signupButton);
        Autologin = (CheckBox) findViewById(R.id.checkBox);
        preferences = getSharedPreferences("setting", 0);
        editor = preferences.edit();

        if (!preferences.getString("ID", "").isEmpty()) {
            id.setText(preferences.getString("ID", ""));
            pw.setText(preferences.getString("PW", ""));
            Intent login = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(login);
            finish();
        }
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    Login.getBackground().setAlpha(200);
                    Login.setEnabled(true);
                } else {
                    Login.getBackground().setAlpha(51);
                    Login.setEnabled(false);
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_check) {
                    login_check = false;
                    dialog = ProgressDialog.show(LoginActivity.this, "",
                            "로그인 중. 잠시 기다려주세요.", true);
                    if (Autologin.isChecked()) {
                        editor.putString("ID", id.getText().toString());
                        editor.putString("PW", pw.getText().toString());
                        editor.commit();
                    }
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                    finish();
                    dialog.dismiss();
                } else {
                    login_check = true;
                    Toast.makeText(LoginActivity.this, "학번 또는 비밀번호를 다시 확인하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
