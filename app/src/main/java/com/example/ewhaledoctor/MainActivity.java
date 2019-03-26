package com.example.ewhaledoctor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.module_home.HomeFragment;
import com.example.module_kind.KindFragment;
import com.example.module_shopcar.ShopCarFragment;
import com.example.module_user.UserFragment;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbHome = findViewById(R.id.rb_home);
        rbKind = findViewById(R.id.rb_kind);
        rbShopcar = findViewById(R.id.rb_shopcar);
        rbUser = findViewById(R.id.rb_user);
        rgHome = findViewById(R.id.rg_home);
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
