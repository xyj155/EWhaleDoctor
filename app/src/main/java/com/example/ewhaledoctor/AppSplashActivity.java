package com.example.ewhaledoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.util.StringUtil;
import com.example.module_login.view.UserLoginActivity;

public class AppSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String user = (String) SharePreferenceUtil.getUser("uid", "String");
                if (StringUtil.isEmpty(user)) {
                    startActivity(new Intent(AppSplashActivity.this, UserLoginActivity.class));
                } else {
                    startActivity(new Intent(AppSplashActivity.this, MainActivity.class));
                }
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();

            }
        }, 1500);
    }
}
