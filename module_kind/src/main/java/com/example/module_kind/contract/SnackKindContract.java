package com.example.module_kind.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;


import java.util.List;

import nico.stytool.gson_module.SnackKindGson;
import rx.Observable;

public interface SnackKindContract {
    interface Model {
        Observable<BaseGson<SnackKindGson>> querySnackKind();
    }

    interface View extends BaseView {
        void  querySnackKind(List<SnackKindGson> snackKindGsons);
    }

    interface Presenter {
        void  querySnackKind();
    }
}
