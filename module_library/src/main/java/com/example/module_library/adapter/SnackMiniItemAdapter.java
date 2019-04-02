package com.example.module_library.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_library.R;

import com.example.module_library.util.GlideUtil;

import java.util.List;

import nico.stytool.gson_module.FoodsGson;

public class SnackMiniItemAdapter extends BaseQuickAdapter<FoodsGson, BaseViewHolder> {

    public SnackMiniItemAdapter(@Nullable List<FoodsGson> data) {
        super(R.layout.home_purse_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodsGson item) {
        GlideUtil.loadRoundCornerAvatarImage(item.getFoodPicture(), (ImageView) helper.getView(R.id.iv_goods),10);
        helper.setText(R.id.tv_goods_name, item.getFoodName())
        .setText(R.id.tv_price,item.getFoodsPrice())
        .setText(R.id.tv_per,item.getFoodsSize()+"/ 件");
    }
}
