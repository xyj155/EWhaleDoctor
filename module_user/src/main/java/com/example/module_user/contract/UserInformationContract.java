package com.example.module_user.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;


import java.util.List;

import nico.stytool.gson_module.UserReceiveAddressGson;
import rx.Observable;

public interface UserInformationContract {
    interface Model {
        Observable<BaseGson<UserReceiveAddressGson>> queryUserReceiveAddress(String userId);
    }

    interface View  extends BaseView {
        void loadUserReceiveAddress(List<UserReceiveAddressGson> userReceiveAddressGsons);
    }

    interface Presenter {
        void queryUserReceiveAddress(String userId);
    }
}
