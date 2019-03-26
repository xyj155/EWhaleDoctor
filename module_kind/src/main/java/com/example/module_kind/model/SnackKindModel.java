package com.example.module_kind.model;

import com.example.module_kind.contract.SnackKindContract;
import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.SnackKindGson;
import com.example.module_library.http.RetrofitUtils;

import rx.Observable;

public class SnackKindModel implements SnackKindContract.Model {
    @Override
    public Observable<BaseGson<SnackKindGson>> querySnackKind() {
        return RetrofitUtils.getInstance().create().querySnackKind();
    }
}
