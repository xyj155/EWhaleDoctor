package com.example.module_kind.contract;


import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import nico.stytool.gson_module.EmptyGson;
import rx.Observable;

public interface SnackFoodsShopCarContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> addSnackByUserId(String userId, String goodsId, String tasteId, String isDelete);

    }

    interface View  extends BaseView {
        void addSnackShopCar(boolean success);
    }

    interface Presenter {
        void addSnackByUserId(String userId, String goodsId, String tasteId, String isDelete);
    }
}
