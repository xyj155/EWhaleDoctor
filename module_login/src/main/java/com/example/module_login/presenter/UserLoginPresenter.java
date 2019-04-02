package com.example.module_login.presenter;

import android.util.Log;


import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;

import com.example.module_library.http.BaseObserver;
import com.example.module_login.contract.UserLoginContract;
import com.example.module_login.model.UserLoginModel;

import nico.stytool.gson_module.UserGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserLoginPresenter extends BasePresenter<UserLoginContract.View> implements UserLoginContract.Presenter {
    private UserLoginModel userModel = new UserLoginModel();
    private static final String TAG = "UserLoginPresenter";

    public UserLoginPresenter(UserLoginContract.View mMvpView) {
        super(mMvpView);
    }

    @Override
    public void userLogin(String username, String password) {
        mMvpView.showDialog();
        userModel.userLoginByUserName(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onCompleted() {
                        mMvpView.hideDialog();
                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        Log.i(TAG, "onNext: " + userGsonBaseGson.toString());
                        if (userGsonBaseGson.isStatus()) {
                            mMvpView.userLogin(userGsonBaseGson.getData().get(0));
                        } else {
                            mMvpView.showError(userGsonBaseGson.getMsg());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                        Log.i(TAG, "onError: " + error);
                        mMvpView.showError("服务器错误，请重试！");
                    }
                });
    }
}
