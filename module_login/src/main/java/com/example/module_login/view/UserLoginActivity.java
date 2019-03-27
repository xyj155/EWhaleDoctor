package com.example.module_login.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.gson.UserGson;
import com.example.module_library.util.Md5Util;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.util.StringUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.example.module_login.R;
import com.example.module_login.contract.UserLoginContract;
import com.example.module_login.presenter.UserLoginPresenter;


import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserLoginActivity extends BaseActivity<UserLoginContract.View, UserLoginPresenter> implements View.OnClickListener, UserLoginContract.View {


    private ImageView ivClose;
    private TextView tvLogin;
    private EditText etUsername;
    private EditText etPassword;
    private TextView btnLogin;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return false;
    }

    @Override
    public UserLoginPresenter getPresenter() {
        return new UserLoginPresenter(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    public void initView() {
        ivClose = findViewById(R.id.iv_close);
        tvLogin = findViewById(R.id.tv_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_close) {
            finish();
        } else if (i == R.id.btn_login) {
            if (!StringUtil.isEmpty(etUsername.getText().toString()) && !StringUtil.isEmpty(etPassword.getText().toString())) {
                String encode = Md5Util.encode(etPassword.getText().toString());
                Log.i(TAG, "onViewClicked: " + encode);
                mPresenter.userLogin(etUsername.getText().toString(), encode);
            } else {
                ToastUtils.show("输入不可为空！");
            }
        }
    }

    @Override
    public void userLogin(UserGson userGson) {
        ARouter.getInstance().build(RouterConfig.HOMEPAGE).navigation();
        Map<String, Object> map = new HashMap<>();
        map.put("username", userGson.getUsername());
        map.put("password", userGson.getPassword());
        map.put("avatar", userGson.getAvatar());
        map.put("telphone", userGson.getTelphone());
        map.put("uid", String.valueOf(userGson.getId()));
        map.put("userToken", String.valueOf(userGson.getUserToken()));
        map.put("islogin", true);
        map.put("isOpenSound", true);
        map.put("member", userGson.getVipRank());
        map.put("imToken", userGson.getImToken());
        SharePreferenceUtil.saveUser(map);
        finish();
    }

    @Override
    public void showError(String msg) {
        ToastUtils.show("登录失败；"+msg);
    }

    @Override
    public void showDialog() {
        createDialog();
    }

    @Override
    public void hideDialog() {
        mhideDialog();
    }
}
