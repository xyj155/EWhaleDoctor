package com.example.module_kind.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_kind.R;
import com.example.module_kind.R2;
import com.example.module_kind.adapter.SnackChildKindAdapter;
import com.example.module_kind.contract.SnackChildContract;
import com.example.module_kind.presenter.SnackChildPresenter;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.BounceRecyclerView;
import com.example.module_library.weight.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nico.stytool.gson_module.SnackChildGson;

public class SnackListActivity extends BaseActivity<SnackChildContract.View, SnackChildPresenter> implements SnackChildContract.View {


    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.ry_item)
    BounceRecyclerView ryItem;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.ry_item_list)
    BounceRecyclerView ryItemList;
    private SnackChildKindAdapter snackChildKindAdapter;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return false;
    }

    @Override
    public SnackChildPresenter getPresenter() {
        return new SnackChildPresenter(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_snack_list;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("name");
        tvTitle.setText(name);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ryItem.setLayoutManager(new LinearLayoutManager(SnackListActivity.this));
    }

    @Override
    public void initData() {
        mPresenter.querySnackChildByPid(getIntent().getStringExtra("pid"));
    }


    @Override
    public void getSnackChild(List<SnackChildGson> snackChildGsons) {
        snackChildKindAdapter = new SnackChildKindAdapter(snackChildGsons);
        ryItem.setOritation(RecyclerView.VERTICAL);
        ryItem.setAdapter(snackChildKindAdapter);
        snackChildKindAdapter.setOnItemClickListener(new SnackChildKindAdapter.onItemClickListener() {
            @Override
            public void onClickListener(int pid, String name) {
//                foodsParentId = pid;
//                mPresenter.querySnackByKindId(String.valueOf(pid), String.valueOf(SharePreferenceUtil.getUser("uid", "String")));
            }
        });
    }

    @Override
    public void showError(String msg) {
        ToastUtils.show(msg);
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
