package com.example.module_home.homefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EnergyFragment extends BaseFragment<EmptyPresenter> {

    ImageView ivSnack1;
    ImageView ivSnack2;
    ImageView ivSnack3;
    Unbinder unbinder;

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {
        ivSnack1 = view.findViewById(R.id.iv_snack_1);
        ivSnack2 = view.findViewById(R.id.iv_snack_2);
        ivSnack3 = view.findViewById(R.id.iv_snack_3);
        GlideUtil.loadRoundCornerAvatarImage(R.drawable.pic2,ivSnack1,14);
        GlideUtil.loadRoundCornerAvatarImage(R.drawable.pic3,ivSnack3,14);
        GlideUtil.loadRoundCornerAvatarImage(R.drawable.pic1,ivSnack2,14);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_energy;
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
}
