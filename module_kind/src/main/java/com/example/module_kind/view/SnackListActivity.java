package com.example.module_kind.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_kind.R;
import com.example.module_kind.R2;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.weight.BounceRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnackListActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {


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
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
