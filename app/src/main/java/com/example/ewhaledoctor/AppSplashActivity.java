package com.example.ewhaledoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.module_library.MyApp;
import com.example.module_library.interfaces.ThirdLoginListener;
import com.example.module_library.interfaces.ThirdLoginListenerImpl;
import com.example.module_library.util.GlideUtil;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.util.StringUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.example.module_login.view.UserLoginActivity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class AppSplashActivity extends AppCompatActivity implements ThirdLoginListener {
    private Tencent instance;
    private static final String TAG = "AppSplashActivity";
    private ThirdLoginListenerImpl thirdLoginListener1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_splash);
        GlideUtil.BaseGlide("https://img.zcool.cn/community/01bd3d5c909321a8012141684242ba.jpg@1280w_1l_2o_100sh.jpg", (ImageView) findViewById(R.id.iv_splash));
        thirdLoginListener1 = new ThirdLoginListenerImpl(this);
        ThirdLoginListenerImpl thirdLoginListener = new ThirdLoginListenerImpl(this);
        String openid = "1234567896ASDFGHJKLLIUYT";
        String access_token = "2C0884DC4B930010D852D8D504FC9F4D";
//        instance = Tencent.createInstance("1108195133", MyApp.getInstance());
//        instance.setOpenId("lefeXcyoJO2a0vMJ");
//        instance.login(this, "all", thirdLoginListener1);
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


    @Override
    public void loginSuccess(JSONObject jsonObject) {
        try {
            Log.i(TAG, "loginSuccess: " + jsonObject.getString("openid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loginFailed(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, thirdLoginListener1);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
