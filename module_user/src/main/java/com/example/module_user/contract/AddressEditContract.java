package com.example.module_user.contract;


import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;
import com.example.module_library.gson.EmptyGson;

import rx.Observable;

public interface AddressEditContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> updateAddress(String id, String username, String tel, String location, String detail);
    }

    interface View extends BaseView {
        void updateSuccess(boolean isSuccess);
    }

    interface Presenter {
        void updateAddress(String id, String username, String tel, String location, String detail);
    }
}
