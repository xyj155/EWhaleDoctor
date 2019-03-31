package com.example.module_library.interfaces;

import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class ThirdLoginListenerImpl implements IUiListener {

    private ThirdLoginListener thirdLoginListener;

    public ThirdLoginListenerImpl(ThirdLoginListener thirdLoginListener) {
        this.thirdLoginListener = thirdLoginListener;
    }

    @Override
    public void onComplete(Object o) {
        thirdLoginListener.loginSuccess((JSONObject)o);
    }

    @Override
    public void onError(UiError uiError) {
        thirdLoginListener.loginFailed("登陆失败！"+uiError);
    }

    @Override
    public void onCancel() {
        thirdLoginListener.loginFailed("取消登陆！");
    }
}