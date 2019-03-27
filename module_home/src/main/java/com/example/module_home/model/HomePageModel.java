package com.example.module_home.model;

import com.example.module_home.contract.HomePageContract;
import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.FoodsGson;
import com.example.module_library.http.RetrofitUtils;

import rx.Observable;

public class HomePageModel implements HomePageContract.Model {
    @Override
    public Observable<BaseGson<FoodsGson>> test() {
        return RetrofitUtils.getInstance().create().test();
    }
}
