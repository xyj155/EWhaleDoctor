package com.example.module_user.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.GlideUtil;
import com.example.module_library.weight.RodHoriztalProgressBar;
import com.example.module_user.R;
import com.example.module_user.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInviteActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {


    @BindView(R2.id.iv_invite)
    ImageView ivInvite;
    @BindView(R2.id.mine_credit_pb_credit)
    RodHoriztalProgressBar mineCreditPbCredit;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return false;
    }

    @Override
    public EmptyPresenter getPresenter() {
        return null;
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_user_invite;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar().setToolBarMenu("规则详情").setToolBarTitle("用户邀请");
        GlideUtil.loadRoundCornerAvatarImage(R.drawable.user_invite_bg, ivInvite, 10);
        mineCreditPbCredit.setMax(5);
        mineCreditPbCredit.setProgress(5);
//        mineCreditPbCredit.setProgress(10);
    }

    @Override
    public void initData() {

    }


}
