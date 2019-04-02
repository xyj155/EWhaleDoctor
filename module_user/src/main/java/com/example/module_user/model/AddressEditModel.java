package com.example.module_user.model;


import com.example.module_library.base.BaseGson;

import com.example.module_library.http.RetrofitUtils;
import com.example.module_user.contract.AddressEditContract;

import nico.stytool.gson_module.EmptyGson;
import rx.Observable;

public class AddressEditModel implements AddressEditContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> updateAddress(String id, String username, String tel, String location, String detail) {
        return RetrofitUtils.getInstance().create().updateUserAddress(username,tel,location,detail,id);
    }
}
