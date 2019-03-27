package com.example.module_library.api;

import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.FoodsGson;
import com.example.module_library.gson.SnackKindGson;
import com.example.module_library.gson.UserGson;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {

    @GET("/StuShop/public/index.php/snack/Snack/querySnackKind")
    Observable<BaseGson<SnackKindGson>> querySnackKind();
    @GET("/StuShop/public/index.php/snack/Snack/test")
    Observable<BaseGson<FoodsGson>> test();

    @FormUrlEncoded
    @POST("/StuShop/public/index.php/index/User/userLoginByUserName")
    Observable<BaseGson<UserGson>> userLoginByUserName(@Field("username") String username, @Field("password") String password);
}
