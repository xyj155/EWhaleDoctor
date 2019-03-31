package com.example.module_library.interfaces;

import org.json.JSONObject;

public interface ThirdLoginListener {
    void loginSuccess(JSONObject jsonObject);
    void loginFailed(String  msg);
}
