package com.example.module_user.model;



import com.example.module_library.base.BaseGson;
import com.example.module_library.gson.EmptyGson;
import com.example.module_library.http.RetrofitUtils;
import com.example.module_user.contract.AddressOfNewReceiptContract;

import rx.Observable;

public class AddressOfNewReceiptModel implements AddressOfNewReceiptContract.Model {


    @Override
    public Observable<BaseGson<EmptyGson>> saveNewAddress(String username, String tel, String local, String detail, String isDefalt, String userId) {
        return RetrofitUtils.getInstance().create().saveNewAddressByUserId(username, tel, local, detail, isDefalt, userId);
    }
}
