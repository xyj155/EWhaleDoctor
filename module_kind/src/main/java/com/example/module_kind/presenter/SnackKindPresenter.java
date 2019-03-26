package com.example.module_kind.presenter;

import com.example.module_kind.contract.SnackKindContract;
import com.example.module_kind.model.SnackKindModel;
import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.gson.SnackKindGson;
import com.example.module_library.http.BaseObserver;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SnackKindPresenter extends BasePresenter<SnackKindContract.View> implements SnackKindContract.Presenter {
    private SnackKindModel snackKindModel = new SnackKindModel();


    public SnackKindPresenter(SnackKindContract.View mMvpView) {
        super(mMvpView);
    }

    @Override
    public void querySnackKind() {
        mMvpView.showDialog();
        snackKindModel.querySnackKind()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SnackKindGson>>() {
                    @Override
                    public void onCompleted() {
                        mMvpView.hideDialog();
                    }

                    @Override
                    public void onNext(BaseGson<SnackKindGson> snackKindGsonBaseGson) {
                        if (snackKindGsonBaseGson.isStatus()) {
                            mMvpView.querySnackKind(snackKindGsonBaseGson.getData());
                        }

                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.showError(error);
                        mMvpView.hideDialog();
                    }
                });
    }
}
