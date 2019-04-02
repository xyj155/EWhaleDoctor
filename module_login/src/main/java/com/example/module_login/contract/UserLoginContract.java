package com.example.module_login.contract;



import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;


import nico.stytool.gson_module.UserGson;
import rx.Observable;

public interface UserLoginContract {
    interface Model {
        Observable<BaseGson<UserGson>> userLoginByUserName(String username, String password);


    }

    interface View extends BaseView {

        void userLogin(UserGson userGson);


    }

    interface Presenter {
        void userLogin(String username, String password);
    }
}
