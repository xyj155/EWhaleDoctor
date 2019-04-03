package com.example.module_kind.model;

import com.example.module_kind.contract.SnackChildContract;
import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;
import com.example.module_library.util.SharePreferenceUtil;

import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackGson;
import nico.stytool.gson_module.SnackShopCarGson;
import rx.Observable;

public class SnackChildModel implements SnackChildContract.Model {
    @Override
    public Observable<BaseGson<SnackChildGson>> querySnackChildByPid(String pid) {
        return RetrofitUtils.getInstance().create().querySnackChildByPid(pid);
    }

    @Override
    public Observable<BaseGson<SnackGson>> querySnackListByPid(String pid) {
        return RetrofitUtils.getInstance().create().querySnackListByPid(pid, String.valueOf(SharePreferenceUtil.getUser("uid","String")));
    }

    @Override
    public Observable<BaseGson<SnackShopCarGson>> queryUserAllSnack(String userId) {
        return RetrofitUtils.getInstance().create().queryUserAllSnack(userId);
    }
}
