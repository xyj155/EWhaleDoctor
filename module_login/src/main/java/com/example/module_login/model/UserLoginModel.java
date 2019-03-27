package com.example.module_login.model;


import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.UserGson;
import com.example.module_library.http.RetrofitUtils;
import com.example.module_login.contract.UserLoginContract;

import rx.Observable;

public class UserLoginModel implements UserLoginContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> userLoginByUserName(String username, String password) {
        return RetrofitUtils.getInstance().create().userLoginByUserName(username,password);
    }

}
