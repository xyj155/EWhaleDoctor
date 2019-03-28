package com.example.module_user.presenter;



import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.gson.EmptyGson;
import com.example.module_library.http.BaseObserver;
import com.example.module_user.contract.AddressOfNewReceiptContract;
import com.example.module_user.model.AddressOfNewReceiptModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddressOfNewReceiptPresenter extends BasePresenter<AddressOfNewReceiptContract.View> implements AddressOfNewReceiptContract.Presenter {
    public AddressOfNewReceiptPresenter(AddressOfNewReceiptContract.View mMvpView) {
        super(mMvpView);
    }

    private AddressOfNewReceiptModel addressOfNewReceiptModel = new AddressOfNewReceiptModel();

    @Override
    public void saveNewAddress(String username, String tel, String local, String detail, boolean isDefalt, String userId) {
        mMvpView.showDialog();
        addressOfNewReceiptModel.saveNewAddress(username, tel, local, detail, isDefalt ? "1" : "0", userId)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        mMvpView.hideDialog();
                        if (emptyGsonBaseGson.isStatus()) {
                            mMvpView.saveNewAddressSuccess(true);
                        } else {
                            mMvpView.saveNewAddressSuccess(false);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }
}
