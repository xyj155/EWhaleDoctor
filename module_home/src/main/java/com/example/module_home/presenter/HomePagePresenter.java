package com.example.module_home.presenter;

import com.example.module_home.contract.HomePageContract;
import com.example.module_home.model.HomePageModel;
import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.gson.FoodsGson;
import com.example.module_library.http.BaseObserver;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePagePresenter extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter {

    public HomePagePresenter(HomePageContract.View mMvpView) {
        super(mMvpView);
    }

    private HomePageModel homePageModel = new HomePageModel();

    @Override
    public void test() {
        mMvpView.showDialog();
        homePageModel.test()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<FoodsGson>>() {
                    @Override
                    public void onCompleted() {
                        mMvpView.hideDialog();
                    }

                    @Override
                    public void onNext(BaseGson<FoodsGson> foodsGsonBaseGson) {
                        if (foodsGsonBaseGson.isStatus()) {
                            mMvpView.test(foodsGsonBaseGson.getData());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                        mMvpView.showError(error);
                    }
                });
    }
}
