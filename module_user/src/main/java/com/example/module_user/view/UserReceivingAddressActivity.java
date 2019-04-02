package com.example.module_user.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_library.adapter.UserReceiveAddressAdapter;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.config.RouterConfig;

import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_user.R;
import com.example.module_user.R2;
import com.example.module_user.contract.UserInformationContract;
import com.example.module_user.presenter.UserInformationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nico.stytool.gson_module.UserReceiveAddressGson;

public class UserReceivingAddressActivity extends BaseActivity<UserInformationContract.View, UserInformationPresenter> implements UserInformationContract.View {


    @BindView(R2.id.ry_receive)
    RecyclerView ryReceive;
    @BindView(R2.id.tv_add_news_address)
    TextView tvAddNewsAddress;
    private UserReceiveAddressAdapter userReceiveAddressAdapter ;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return false;
    }

    @Override
    public UserInformationPresenter getPresenter() {
        return new UserInformationPresenter(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_user_receiving_address;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        userReceiveAddressAdapter = new UserReceiveAddressAdapter(null,UserReceivingAddressActivity.this);
        initToolBar().setToolBarTitle("我的收货地址");
        ryReceive.setLayoutManager(new LinearLayoutManager(UserReceivingAddressActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryUserReceiveAddress((String) SharePreferenceUtil.getUser("uid","String"));
    }

    @Override
    public void initData() {

    }

    private List<UserReceiveAddressGson> userReceiveAddressGsonList = new ArrayList<>();

    @Override
    public void loadUserReceiveAddress(List<UserReceiveAddressGson> userReceiveAddressGsons) {
        userReceiveAddressGsonList.clear();
        for (int i = 0; i < userReceiveAddressGsons.size(); i++) {
            if (userReceiveAddressGsons.get(i).getIsDefault().equals("1")) {
                userReceiveAddressGsonList.add(0, userReceiveAddressGsons.get(i));
            } else {
                userReceiveAddressGsonList.add(userReceiveAddressGsons.get(i));
            }
        }
        userReceiveAddressAdapter.replaceData(userReceiveAddressGsonList);
        ryReceive.setAdapter(userReceiveAddressAdapter);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showDialog() {
        createDialog();
    }


    @Override
    public void hideDialog() {
        mhideDialog();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.tv_add_news_address})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_add_news_address) {
//            startActivity(AddressOfNewReceiptActivity.class);
            ARouter.getInstance().build(RouterConfig.ADDRESSADDED).navigation();
        }
    }
}
