package com.example.module_kind.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_kind.R;
import com.example.module_kind.view.SnackListActivity;

import com.example.module_library.util.GlideUtil;

import java.util.List;

import nico.stytool.gson_module.SnackKindGson;

public class SnackKindAdapter extends BaseQuickAdapter<SnackKindGson, BaseViewHolder> {
private Context context;
    public SnackKindAdapter(@Nullable List<SnackKindGson> data,Context context) {
        super(R.layout.snackkind_item, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SnackKindGson item) {
        helper.setText(R.id.tv_title, item.getSnackName())
        .setOnClickListener(R.id.ll_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SnackListActivity.class);
                intent.putExtra("name",item.getSnackName());
                intent.putExtra("pid",String.valueOf(item.getId()));
                context.startActivity(intent);
            }
        });
        GlideUtil.BaseGlide(item.getSnackPicture(), (ImageView) helper.getView(R.id.iv_kind));
    }
}
