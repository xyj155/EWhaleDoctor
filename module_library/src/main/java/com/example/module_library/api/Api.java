package com.example.module_library.api;

import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.EmptyGson;
import com.example.module_library.gson.FoodsGson;
import com.example.module_library.gson.SnackKindGson;
import com.example.module_library.gson.UserGson;
import com.example.module_library.gson.UserReceiveAddressGson;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("/StuShop/public/index.php/snack/Snack/querySnackKind")
    Observable<BaseGson<SnackKindGson>> querySnackKind();
    @GET("/StuShop/public/index.php/snack/Snack/test")
    Observable<BaseGson<FoodsGson>> test();

    @FormUrlEncoded
    @POST("/StuShop/public/index.php/index/User/userLoginByUserName")
    Observable<BaseGson<UserGson>> userLoginByUserName(@Field("username") String username, @Field("password") String password);

    @GET("/StuShop/public/index.php/index/User/queryUserReceiveAddress")
    Observable<BaseGson<UserReceiveAddressGson>> queryUserUserAddress(@Query("userId") String goodId);

    @FormUrlEncoded
    @POST("/StuShop/public/index.php/index/User/saveNewAddressByUserId")
    Observable<BaseGson<EmptyGson>> saveNewAddressByUserId(@Field("username") String username,
                                                           @Field("tel") String tel,
                                                           @Field("local") String local,
                                                           @Field("detail") String detail,
                                                           @Field("default") String defaults,
                                                           @Field("userId") String userId
    );
    @FormUrlEncoded
    @POST("/StuShop/public/index.php/index/User/updateUserAddress")
    Observable<BaseGson<EmptyGson>> updateUserAddress(@Field("username") String username,
                                                      @Field("tel") String tel,
                                                      @Field("location") String location,
                                                      @Field("detail") String detail,
                                                      @Field("id") String id);
}
