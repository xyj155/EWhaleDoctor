package com.example.ewhaledoctor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.view.HomeFragment;
import com.example.module_kind.view.KindFragment;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_shopcar.ShopCarFragment;
import com.example.module_user.view.UserFragment;

@Route(path = RouterConfig.HOMEPAGE)
public class MainActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> implements CompoundButton.OnCheckedChangeListener {

    private RadioButton rbHome;
    private RadioButton rbKind;
    private RadioButton rbShopcar;
    private RadioButton rbUser;
    private RadioGroup rgHome;
    private static final String TAG = "MainActivity";


    private FragmentManager supportFragmentManager;
    private Fragment homeFragment;
    private Fragment kindFragment;
    private Fragment shopCarFragment;
    private Fragment userFragment;



    @Override
    public boolean isSetStatusBarTranslucent() {
        return true;
    }

    @Override
    public EmptyPresenter getPresenter() {
        return null;
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rbHome = findViewById(R.id.rb_home);
        rbKind = findViewById(R.id.rb_kind);
        rbShopcar = findViewById(R.id.rb_shopcar);
        rbUser = findViewById(R.id.rb_user);
        rgHome = findViewById(R.id.rg_home);
    }

    @Override
    public void initData() {
        supportFragmentManager = getSupportFragmentManager();
        rbHome.setChecked(true);
        showFirstPosition();
        rbHome.setOnCheckedChangeListener(this);
        rbKind.setOnCheckedChangeListener(this);
        rbShopcar.setOnCheckedChangeListener(this);
        rbUser.setOnCheckedChangeListener(this);
        rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (checkedId) {
                    case R.id.rb_home:
                        Log.i(TAG, "onCheckedChanged4: ");
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                            transaction.add(R.id.flContainer, homeFragment);
                        } else {
                            transaction.show(homeFragment);
                        }
                        break;
                    case R.id.rb_kind:
                        Log.i(TAG, "onCheckedChanged:3 ");
                        if (kindFragment == null) {
                            kindFragment = new KindFragment();
                            transaction.add(R.id.flContainer, kindFragment);
                        } else {
                            transaction.show(kindFragment);
                        }
                        break;
                    case R.id.rb_shopcar:
                        Log.i(TAG, "onCheckedChanged: 2");
                        if (shopCarFragment == null) {
                            shopCarFragment = new ShopCarFragment();
                            transaction.add(R.id.flContainer, shopCarFragment);
                        } else {
                            transaction.show(shopCarFragment);
                        }
                        break;
                    case R.id.rb_user:

                        Log.i(TAG, "onCheckedChanged: 1");
                        if (userFragment == null) {
                            userFragment = new UserFragment();
                            transaction.add(R.id.flContainer, userFragment);
                        } else {
                            transaction.show(userFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }


    private void showFirstPosition() {
        supportFragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.flContainer, homeFragment);
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (kindFragment != null) {
            transaction.hide(kindFragment);
        }
        if (shopCarFragment != null) {
            transaction.hide(shopCarFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
