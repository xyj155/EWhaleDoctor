package com.example.module_user.presenter;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;

import com.example.module_library.http.BaseObserver;
import com.example.module_user.contract.UserInformationContract;
import com.example.module_user.model.UserInformationModel;

import nico.stytool.gson_module.UserReceiveAddressGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInformationPresenter  extends BasePresenter<UserInformationContract.View> implements UserInformationContract.Presenter {
    public UserInformationPresenter(UserInformationContract.View mMvpView) {
        super(mMvpView);
    }

    private UserInformationModel userReceivingAddressModel = new UserInformationModel();

    @Override
    public void queryUserReceiveAddress(String userId) {
        mMvpView.showDialog();
        userReceivingAddressModel.queryUserReceiveAddress(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserReceiveAddressGson>>() {
                    @Override
                    public void onCompleted() {
                        mMvpView.hideDialog();
                    }

                    @Override
                    public void onNext(BaseGson<UserReceiveAddressGson> userReceiveAddressGsonBaseGson) {
                        mMvpView.hideDialog();
                        mMvpView.loadUserReceiveAddress(userReceiveAddressGsonBaseGson.getData());
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }
}
