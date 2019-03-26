package com.example.module_home.homefragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.module_home.R;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;

public class LeisureFragment extends BaseFragment<EmptyPresenter> {

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_leisure;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }
}