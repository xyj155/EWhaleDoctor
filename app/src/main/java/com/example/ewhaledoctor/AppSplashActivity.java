package com.example.ewhaledoctor;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_login.view.UserLoginActivity;

public class AppSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Object user = SharePreferenceUtil.getUser("uid", "String");
                if (user == null) {
                    startActivity(new Intent(AppSplashActivity.this, UserLoginActivity.class));
                } else {
                    startActivity(new Intent(AppSplashActivity.this, MainActivity.class));
                }

            }
        }, 3000);
    }
}
