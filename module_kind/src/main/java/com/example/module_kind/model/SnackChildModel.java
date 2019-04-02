package com.example.module_kind.model;

import com.example.module_kind.contract.SnackChildContract;
import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;

import nico.stytool.gson_module.SnackChildGson;
import rx.Observable;

public class SnackChildModel implements SnackChildContract.Model {
    @Override
    public Observable<BaseGson<SnackChildGson>> querySnackChildByPid(String pid) {
        return RetrofitUtils.getInstance().create().querySnackChildByPid(pid);
    }
}
