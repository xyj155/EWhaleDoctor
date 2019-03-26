package com.example.module_library.api;

import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.SnackKindGson;

import retrofit2.http.GET;
import rx.Observable;

public interface Api {

    @GET("/StuShop/public/index.php/snack/Snack/querySnackKind")
    Observable<BaseGson<SnackKindGson>> querySnackKind();
}
