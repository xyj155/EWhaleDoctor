package com.example.module_library.api;



import com.example.module_library.base.BaseGson;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.FoodsGson;
import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackKindGson;
import nico.stytool.gson_module.UserGson;
import nico.stytool.gson_module.UserReceiveAddressGson;
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

    @GET("/StuShop/public/index.php/snack/Snack/querySnackChildByPid")
    Observable<BaseGson<SnackChildGson>> querySnackChildByPid(@Query("pid") String pid);
}
