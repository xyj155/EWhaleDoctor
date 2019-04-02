package com.example.module_library.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.module_library.R;
import com.example.module_library.config.RouterConfig;


import java.util.List;

import nico.stytool.gson_module.UserReceiveAddressGson;

public class UserReceiveAddressAdapter extends BaseQuickAdapter<UserReceiveAddressGson, BaseViewHolder> {
    private Activity context;

    public UserReceiveAddressAdapter(@Nullable List<UserReceiveAddressGson> data, Activity context) {
        super(R.layout.ry_user_receive_address_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final UserReceiveAddressGson item) {
        TextView view = helper.getView(R.id.tv_address);
        helper.setText(R.id.tv_tel, item.getSaveTel())
                .setText(R.id.tv_savename, item.getSaveName())
                .setOnClickListener(R.id.cb_select, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(context, GoodsPaymentActivity.class);
//                        intent.putExtra("username", item.getSaveName());
//                        intent.putExtra("tel", item.getSaveTel());
//                        intent.putExtra("adId", String.valueOf(item.getId()));
//                        intent.putExtra("local", item.getSaveLocal());
//                        intent.putExtra("detail", item.getSaveAddressDetail());
//                        context.setResult(0x1, intent);
//                        context.finish();
                    }
                });
        helper.setOnClickListener(R.id.iv_edit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, AddReceivingAddressActivity.class);
//                intent.putExtra("addressId", String.valueOf(item.getId()));
//                context.startActivity(intent);
                ARouter.getInstance().build(RouterConfig.ADDRESSEDIT)
                        .withString("tel", item.getSaveTel())
                        .withString("adId", String.valueOf(item.getId()))
                        .withString("addressId", String.valueOf(item.getId()))
                        .withString("local", item.getSaveLocal())
                        .withString("detail", item.getSaveAddressDetail())
                        .withString("savename", item.getSaveName())
                        .navigation();
            }
        });
        if (item.getIsDefault().equals("1")) {
            SpannableString spannableString = new SpannableString("[默认]" + item.getSaveLocal() + item.getSaveAddressDetail());
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            view.setText(spannableString);
        } else {
            helper.setText(R.id.tv_address, item.getSaveLocal() + item.getSaveAddressDetail());
        }

    }
}
