package com.example.module_kind.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;
import com.example.module_library.gson.SnackKindGson;

import java.util.List;

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
