package com.example.module_user.contract;


import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;
import com.example.module_library.gson.EmptyGson;

import rx.Observable;

public interface AddressOfNewReceiptContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> saveNewAddress(String username, String tel, String local, String detail, String isDefalt, String userId);
    }

    interface View extends BaseView {
        void saveNewAddressSuccess(boolean success);
    }

    interface Presenter {
        void saveNewAddress(String username, String tel, String local, String detail, boolean isDefalt, String userId);
    }
}
