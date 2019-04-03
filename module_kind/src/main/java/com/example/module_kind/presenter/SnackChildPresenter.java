package com.example.module_kind.presenter;

import android.util.Log;

import com.example.module_kind.contract.SnackChildContract;
import com.example.module_kind.model.SnackChildModel;
import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.http.BaseObserver;

import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackGson;
import nico.stytool.gson_module.SnackShopCarGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SnackChildPresenter extends BasePresenter<SnackChildContract.View> implements SnackChildContract.Presenter {

    public SnackChildPresenter(SnackChildContract.View mMvpView) {
        super(mMvpView);
    }

    private SnackChildModel childModel = new SnackChildModel();

    @Override
    public void querySnackChildByPid(String pid) {
        mMvpView.showDialog();
        childModel.querySnackChildByPid(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SnackChildGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SnackChildGson> snackChildGsonBaseGson) {
                        mMvpView.hideDialog();
                        Log.i(TAG, "onNext: "+snackChildGsonBaseGson.getData().size());
                        if (snackChildGsonBaseGson.isStatus())
                            mMvpView.getSnackChild(snackChildGsonBaseGson.getData());
                        else
                            mMvpView.showError(snackChildGsonBaseGson.getMsg());
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                        mMvpView.showError(error);
                    }
                });
    }

    @Override
    public void querySnackListByPid(String pid) {
        mMvpView.showDialog();
        childModel.querySnackListByPid(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SnackGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SnackGson> snackChildGsonBaseGson) {
                        mMvpView.hideDialog();
                        Log.i(TAG, "onNext: "+snackChildGsonBaseGson.getData().size());
                        if (snackChildGsonBaseGson.isStatus())
                            mMvpView.getSnackListByPid(snackChildGsonBaseGson.getData());
                        else
                            mMvpView.showError(snackChildGsonBaseGson.getMsg());
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                        mMvpView.showError(error);
                    }
                });
    }

    @Override
    public void queryUserShopCarAllSnack(String userId) {
        mMvpView.showDialog();
        childModel.queryUserAllSnack(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SnackShopCarGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SnackShopCarGson> snackKindGsonBaseGson) {
                        mMvpView.hideDialog();
                        mMvpView.queryUserSnackShopCarAllSnack(snackKindGsonBaseGson.getData());
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }

    private static final String TAG = "SnackChildPresenter";
}
