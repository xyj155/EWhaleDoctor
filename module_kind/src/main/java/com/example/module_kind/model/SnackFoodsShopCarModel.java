package com.example.module_kind.model;



import com.example.module_kind.contract.SnackFoodsShopCarContract;
import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;

import nico.stytool.gson_module.EmptyGson;
import rx.Observable;

public class SnackFoodsShopCarModel implements SnackFoodsShopCarContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> addSnackByUserId(String userId, String goodsId, String tasteId, String isDelete) {
        return RetrofitUtils.getInstance().create().addSnackByUserId(userId,goodsId,tasteId,isDelete);
    }
}
