package com.example.module_kind.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import java.util.List;

import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackGson;
import nico.stytool.gson_module.SnackShopCarGson;
import rx.Observable;

public interface SnackChildContract {
    interface Model {
        Observable<BaseGson<SnackChildGson>> querySnackChildByPid(String pid);
        Observable<BaseGson<SnackGson>> querySnackListByPid(String pid);
        Observable<BaseGson<SnackShopCarGson>> queryUserAllSnack(String userId);

    }

    interface View extends BaseView {
        void getSnackChild(List<SnackChildGson> snackChildGsons);
        void getSnackListByPid(List<SnackGson> snackChildGsons);
        void queryUserSnackShopCarAllSnack(List<SnackShopCarGson> list);
    }

    interface Presenter {
        void querySnackChildByPid(String pid);
        void querySnackListByPid(String pid);
        void queryUserShopCarAllSnack(String userId);
    }
}
