package com.example.module_user.model;


import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;
import com.example.module_user.contract.UserInformationContract;

import nico.stytool.gson_module.UserReceiveAddressGson;
import rx.Observable;

public class UserInformationModel implements UserInformationContract.Model {
    @Override
    public Observable<BaseGson<UserReceiveAddressGson>> queryUserReceiveAddress(String userId) {
        return RetrofitUtils.getInstance().create().queryUserUserAddress(userId);
    }
}
