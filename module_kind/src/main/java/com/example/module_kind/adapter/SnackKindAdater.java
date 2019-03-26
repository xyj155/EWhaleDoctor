package com.example.module_kind.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_kind.R;
import com.example.module_library.gson.SnackKindGson;
import com.example.module_library.util.GlideUtil;

import java.util.List;

public class SnackKindAdater extends BaseQuickAdapter<SnackKindGson, BaseViewHolder> {

    public SnackKindAdater(@Nullable List<SnackKindGson> data) {
        super(R.layout.snackkind_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SnackKindGson item) {
        helper.setText(R.id.tv_title, item.getSnackName());
        GlideUtil.BaseGlide(item.getSnackPicture(), (ImageView) helper.getView(R.id.iv_kind));
    }
}
