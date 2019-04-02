package com.example.module_home.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;


import java.util.List;

import nico.stytool.gson_module.FoodsGson;
import rx.Observable;

public interface HomePageContract {
    interface Model {
        Observable<BaseGson<FoodsGson>> test();
    }

    interface View extends BaseView {
        void test(List<FoodsGson> list);
    }

    interface Presenter {
        void test();
    }
}
