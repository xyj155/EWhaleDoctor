package com.example.module_library.logic.presenter;

import com.example.module_library.base.BasePresenter;
import com.example.module_library.logic.contract.EmptyContract;

public class EmptyPresenter extends BasePresenter<EmptyContract.View> implements EmptyContract.Presenter {

    public EmptyPresenter(EmptyContract.View mMvpView) {
        super(mMvpView);
    }
}
