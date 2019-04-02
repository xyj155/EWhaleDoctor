package com.example.module_user.presenter;



import android.util.Log;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;

import com.example.module_library.http.BaseObserver;
import com.example.module_user.contract.AddressEditContract;
import com.example.module_user.model.AddressEditModel;

import nico.stytool.gson_module.EmptyGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddressEditPresenter extends BasePresenter<AddressEditContract.View> implements AddressEditContract.Presenter {

    public AddressEditPresenter(AddressEditContract.View mMvpView) {
        super(mMvpView);
    }

    private AddressEditModel addressEditModel = new AddressEditModel();
    private static final String TAG = "AddressEditPresenter";
    @Override
    public void updateAddress(String id, String username, String tel, String location, String detail) {
        mMvpView.showDialog();
        addressEditModel.updateAddress(id, username, tel, location, detail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        Log.i(TAG, "onNext: "+emptyGsonBaseGson);
                        mMvpView.updateSuccess(emptyGsonBaseGson.isStatus());
                        mMvpView.hideDialog();
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }
}
