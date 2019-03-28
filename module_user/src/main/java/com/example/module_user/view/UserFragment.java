package com.example.module_user.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.GlideUtil;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.CircleImageView;
import com.example.module_user.R;
import com.example.module_user.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//@Route(path = RouterConfig.USERFRAGMENT)
public class UserFragment extends BaseFragment<EmptyPresenter> {
    @BindView(R2.id.iv_setting)
    ImageView ivSetting;
    @BindView(R2.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R2.id.tv_username)
    TextView tvUsername;
    @BindView(R2.id.tv_user_information)
    TextView tvUserInformation;
    @BindView(R2.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R2.id.iv_send)
    ImageView ivSend;
    @BindView(R2.id.iv_receive)
    ImageView ivReceive;
    @BindView(R2.id.iv_evaluate)
    ImageView ivEvaluate;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R2.id.tv_user_score)
    TextView tvUserScore;
    @BindView(R2.id.tv_user_invite)
    TextView tvUserInvite;
    @BindView(R2.id.tv_user_feedback)
    TextView tvUserFeedback;
    @BindView(R2.id.tv_user_service)
    TextView tvUserService;
    Unbinder unbinder;

    @Override
    public void initData() {

    }

    private static final String TAG = "UserFragment";

    @Override
    public void initView(View view) {
        Log.i(TAG, "initView: " + (String) SharePreferenceUtil.getUser("avatar", "String"));
        GlideUtil.BaseGlide((String) SharePreferenceUtil.getUser("avatar", "String"), ivAvatar);
    }


    @Override
    public int initLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.iv_setting, R2.id.iv_avatar, R2.id.tv_user_information, R2.id.tv_all_order, R2.id.iv_send, R2.id.iv_receive, R2.id.iv_evaluate, R2.id.iv_back, R2.id.tv_user_address, R2.id.tv_user_score, R2.id.tv_user_invite, R2.id.tv_user_feedback, R2.id.tv_user_service})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_setting) {
        } else if (i == R.id.iv_avatar) {
        } else if (i == R.id.tv_user_information) {
        } else if (i == R.id.tv_all_order) {
        } else if (i == R.id.iv_send) {
        } else if (i == R.id.iv_receive) {
        } else if (i == R.id.iv_evaluate) {
        } else if (i == R.id.iv_back) {
        } else if (i == R.id.tv_user_address) {
            startActivity(new Intent(getActivity(),UserReceivingAddressActivity.class));
        } else if (i == R.id.tv_user_score) {
        } else if (i == R.id.tv_user_invite) {
        } else if (i == R.id.tv_user_feedback) {
        } else if (i == R.id.tv_user_service) {
        }
    }
}
