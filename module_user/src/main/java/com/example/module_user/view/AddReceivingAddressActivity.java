package com.example.module_user.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.SwitchButton;
import com.example.module_library.weight.toast.ToastUtils;
import com.example.module_library.weight.wheel.CityPickerPopWindow;
import com.example.module_user.R;
import com.example.module_user.R2;
import com.example.module_user.contract.AddressOfNewReceiptContract;
import com.example.module_user.presenter.AddressOfNewReceiptPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@Route(path = RouterConfig.ADDRESSADDED)
public class AddReceivingAddressActivity extends BaseActivity<AddressOfNewReceiptContract.View, AddressOfNewReceiptPresenter> implements AddressOfNewReceiptContract.View, CityPickerPopWindow.CityPickListener {


    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_menu)
    TextView tvMenu;
    @BindView(R2.id.tb_common)
    RelativeLayout tbCommon;
    @BindView(R2.id.et_username)
    EditText etUsername;
    @BindView(R2.id.et_tel)
    EditText etTel;
    @BindView(R2.id.tv_local)
    TextView tvLocal;
    @BindView(R2.id.et_detail)
    EditText etDetail;
    @BindView(R2.id.sw_default)
    SwitchButton swDefault;
    @BindView(R2.id.tv_add_news_address)
    TextView tvAddNewsAddress;
    @BindView(R2.id.ll_root)
    LinearLayout llRoot;
    private boolean isDefault = false;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return false;
    }

    @Override
    public AddressOfNewReceiptPresenter getPresenter() {
        return new AddressOfNewReceiptPresenter(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_add_receiving_address;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        swDefault.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                Log.i(TAG, "onCheckedChanged: "+isChecked);
                if (isChecked) {
                    isDefault = true;
                } else {
                    isDefault = false;
                }
            }
        });
    }

    @Override
    public void initData() {
        initToolBar().setToolBarTitle("添加地址");
    }

    @Override
    public void saveNewAddressSuccess(boolean success) {
        if (success) {
            finish();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("添加失败！");
                }
            });

        }
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


    @SuppressLint("WrongConstant")
    @OnClick({R2.id.tv_local, R2.id.tv_add_news_address})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_local) {

            CityPickerPopWindow mPopWindow = new CityPickerPopWindow(AddReceivingAddressActivity.this);
            final WindowManager.LayoutParams lp = getWindow()
                    .getAttributes();
            lp.alpha = 0.4f;
            mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
            mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getWindow().setAttributes(lp);
            mPopWindow.showPopupWindow(llRoot);
            mPopWindow.setCityPickListener(this);
            mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } else if (id == R.id.tv_add_news_address) {
            if (etUsername.getText().toString().isEmpty()||etDetail.getText().toString().isEmpty()||etTel.getText().toString().isEmpty()||tvLocal.getText().toString().isEmpty()){
                ToastUtils.show("不可为空哦！");
            }else {
                mPresenter.saveNewAddress(etUsername.getText().toString(), etTel.getText().toString(), tvLocal.getText().toString(), etDetail.getText().toString(), isDefault, (String) SharePreferenceUtil.getUser("uid","String"));
            }

        }
    }

    @Override
    public void pickValue(String value) {
        tvLocal.setText(value);
        Log.i(TAG, "pickValue: " + value);
    }

}
