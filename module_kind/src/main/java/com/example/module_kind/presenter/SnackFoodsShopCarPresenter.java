package com.example.module_kind.presenter;



import com.example.module_kind.contract.SnackFoodsShopCarContract;
import com.example.module_kind.model.SnackFoodsShopCarModel;
import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.http.BaseObserver;

import nico.stytool.gson_module.EmptyGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SnackFoodsShopCarPresenter extends BasePresenter<SnackFoodsShopCarContract.View> implements SnackFoodsShopCarContract.Presenter {
    public SnackFoodsShopCarPresenter(SnackFoodsShopCarContract.View mMvpView) {
        super(mMvpView);
    }

    private SnackFoodsShopCarModel snackFoodsShopCarModel = new SnackFoodsShopCarModel();

    @Override
    public void addSnackByUserId(String userId, String goodsId, String tasteId, String isDelete) {
        mMvpView.showDialog();
        snackFoodsShopCarModel.addSnackByUserId(userId, goodsId, tasteId, isDelete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        mMvpView.hideDialog();
//                        if (emptyGsonBaseGson.isStatus()) ;
                        mMvpView.addSnackShopCar(emptyGsonBaseGson.isStatus());
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }
}
