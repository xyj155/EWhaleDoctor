package com.example.module_kind.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import java.util.List;

import nico.stytool.gson_module.SnackChildGson;
import rx.Observable;

public interface SnackChildContract {
    interface Model {
        Observable<BaseGson<SnackChildGson>> querySnackChildByPid(String pid);
    }

    interface View extends BaseView {
        void getSnackChild(List<SnackChildGson> snackChildGsons);
    }

    interface Presenter {
        void querySnackChildByPid(String pid);
    }
}
