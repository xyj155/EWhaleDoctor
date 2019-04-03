package com.example.module_kind.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_kind.R;
import com.example.module_kind.R2;
import com.example.module_kind.adapter.SnackChildKindAdapter;
import com.example.module_kind.adapter.SnackFoodsAdapter;
import com.example.module_kind.contract.SnackChildContract;
import com.example.module_kind.contract.SnackFoodsShopCarContract;
import com.example.module_kind.presenter.SnackChildPresenter;
import com.example.module_kind.presenter.SnackFoodsShopCarPresenter;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.BounceRecyclerView;
import com.example.module_library.weight.LoadingDialog;
import com.example.module_library.weight.MyDialog;
import com.example.module_library.weight.toast.ToastUtils;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackGson;
import nico.stytool.gson_module.SnackShopCarGson;

public class SnackListActivity extends BaseActivity<SnackChildContract.View, SnackChildPresenter> implements SnackChildContract.View, SnackFoodsShopCarContract.View {


    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.iv_shopcar)
    ImageView ivShopcar;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.ry_item)
    BounceRecyclerView ryItem;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.ry_item_list)
    BounceRecyclerView ryItemList;
    private SnackChildKindAdapter snackChildKindAdapter;
    private int foodsParentId;
    private Handler mHanlder;
    private SnackFoodsAdapter snackFoodsAdapter;
    public SnackFoodsShopCarPresenter snackFoodsShopCarPresenter = new SnackFoodsShopCarPresenter(this);
    private View sheetDialog;
    private RecyclerView ryShopCar;
    private TextView tvClean;
    private ShopCarAdapter shopCarAdapter;
    @BindView(R2.id.bottomSheetLayout)
    BottomSheetLayout bottomSheetLayout;
    @BindView(R2.id.tv_money)
    TextView tvMoney;
    @BindView(R2.id.tv_submit)
    TextView tvSubmit;

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
    //刷新布局 总价、购买数量等

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
        sheetDialog = LayoutInflater.from(SnackListActivity.this).inflate(R.layout.snack_bottom_recycler_view, null, false);
        ryShopCar = sheetDialog.findViewById(R.id.ry_shopcar);
        tvClean = sheetDialog.findViewById(R.id.tv_clean);
        shopCarAdapter = new ShopCarAdapter(null);
        ryShopCar.setLayoutManager(new LinearLayoutManager(SnackListActivity.this));
        ryShopCar.setFocusableInTouchMode(false);
        ryShopCar.setFocusable(false);
        ryShopCar.setHasFixedSize(true);
        ryShopCar.setAdapter(shopCarAdapter);
        tvClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsgDialog("清空购物车", "是否清空全部商品", new OnItemClickListener() {
                    @Override
                    public void onConfirm(LoadingDialog dialog) {
                        snackFoodsShopCarPresenter.addSnackByUserId(String.valueOf(SharePreferenceUtil.getUser("uid", "String")), "", "", "3");
                        if (shopCarAdapter != null) {
                            shopCarAdapter.notifyDataSetChanged();
                        }
                    }
                });

            }
        });
    }

    @OnClick({R2.id.iv_shopcar})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_shopcar) {
            showCart();
        }
//        else if (id == R.id.tv_submit) {
////            mPresenter.submitSnackFoodsOrder(String.valueOf(SharePreferenceUtil.getUser("uid", "String")), String.valueOf(SharePreferenceUtil.getUser("userToken", "String")));
//        }

    }

    private void showCart() {
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        } else {
            bottomSheetLayout.showWithSheetView(sheetDialog);
        }
    }

    public class ShopCarAdapter extends BaseQuickAdapter<SnackShopCarGson, BaseViewHolder> {


        public ShopCarAdapter(List<SnackShopCarGson> dataList) {
            super(R.layout.pop_shopcar_list, dataList);

        }


        @Override
        protected void convert(final BaseViewHolder helper, final SnackShopCarGson item) {
            Double aDouble = Double.valueOf(item.getFoodsPrice());
            helper.setText(R.id.tv_name, item.getFoodName())
                    .setText(R.id.tv_price, String.format("%.2f", aDouble))
                    .setText(R.id.tv_count, String.valueOf(item.getCount()))
                    .setText(R.id.tv_tasty_name, item.getFoodsTaste())
                    .setOnClickListener(R.id.iv_add, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < item.getNeedCount(); i++) {
                                snackFoodsShopCarPresenter.addSnackByUserId(String.valueOf(SharePreferenceUtil.getUser("uid", "String")), String.valueOf(item.getFoodsId()), String.valueOf(item.getTasteId()), "0");
                            }

                            notifyDataSetChanged();

                        }
                    })
                    .setOnClickListener(R.id.iv_reduce, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < item.getNeedCount(); i++) {
                                snackFoodsShopCarPresenter.addSnackByUserId(String.valueOf(SharePreferenceUtil.getUser("uid", "String")), String.valueOf(item.getFoodsId()), String.valueOf(item.getTasteId()), "1");

                            }
                            TextView view = (TextView) helper.getView(R.id.tv_count);
                            if (Integer.valueOf(view.getText().toString()) < 2) {
                                remove(helper.getPosition());
                            }
                            notifyDataSetChanged();
                        }
                    });
        }
    }

    private List<SnackChildGson> snackChildGsonList = new ArrayList<>();

    @Override
    public void initData() {
        mHanlder = new Handler(getMainLooper());
        mPresenter.querySnackChildByPid(getIntent().getStringExtra("pid"));
        mPresenter.queryUserShopCarAllSnack(String.valueOf(SharePreferenceUtil.getUser("uid", "String")));
        ryItemList.setLayoutManager(new LinearLayoutManager(SnackListActivity.this));
        snackFoodsAdapter = new SnackFoodsAdapter(SnackListActivity.this, null);
        ryItemList.setAdapter(snackFoodsAdapter);
        snackChildKindAdapter = new SnackChildKindAdapter(snackChildGsonList);
        ryItem.setAdapter(snackChildKindAdapter);
    }


    @Override
    public void getSnackChild(List<SnackChildGson> snackChildGsons) {
        snackChildGsonList.addAll(snackChildGsons);
        foodsParentId = snackChildGsonList.get(0).getId();
        mPresenter.querySnackListByPid(String.valueOf(foodsParentId));
        snackChildKindAdapter.setOnItemClickListener(new SnackChildKindAdapter.onItemClickListener() {
            @Override
            public void onClickListener(int pid, String name) {
                foodsParentId = pid;
                mPresenter.querySnackListByPid(String.valueOf(pid));
            }
        });
    }

    private List<SnackGson> snackChildGson = new ArrayList<>();

    @Override
    public void getSnackListByPid(List<SnackGson> snackChildGsons) {
        snackChildGson.clear();
        snackChildGson.addAll(snackChildGsons);
        snackFoodsAdapter.replaceData(snackChildGson);
        snackFoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryUserSnackShopCarAllSnack(List<SnackShopCarGson> list) {
        int shopCount = 0;
        double shopCarMoney = 0.00;
        if (list.size() > 0) {
            for (SnackShopCarGson snackShopCarGson : list) {
                shopCount += snackShopCarGson.getCount();
                shopCarMoney += Double.valueOf(snackShopCarGson.getFoodsPrice()) * snackShopCarGson.getCount();
            }
//            tvShopcarCount.setText(String.valueOf(shopCount));
            tvMoney.setText("￥" + String.format("%.2f", shopCarMoney));
//            tvShopcarCount.setVisibility(View.VISIBLE);
//            tvSubmit.setBackgroundResource(R.drawable.bg_btn_shopcar);
            tvSubmit.setTextColor(0xffffffff);
            tvSubmit.setClickable(true);
            tvSubmit.setText("去结算");
        } else {
//            tvSubmit.setBackgroundColor(0xff535254);
            tvSubmit.setTextColor(0xffa2a2a2);
            tvSubmit.setClickable(false);
//            tvShopcarCount.setText(String.valueOf(0));
            tvMoney.setText("￥ 0.00");
            tvSubmit.setText("无商品");
//            tvShopcarCount.setVisibility(View.GONE);
        }
        if (shopCarAdapter != null) {
            shopCarAdapter.replaceData(list);
            shopCarAdapter.notifyDataSetChanged();
        }
//        if (slSort != null)
//            slSort.finishRefresh();
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

    public void playAnimation(int[] start_location) {
        ImageView img = new ImageView(this);
        img.setImageResource(R.mipmap.shop_add);
        setAnim(img, start_location);
    }

    private void setAnim(final View v, int[] start_location) {
        final ViewGroup flShopcar = findViewById(R.id.rl_container);
        addViewToAnimLayout(flShopcar, v, start_location);
        Animation set = createAnim(start_location[0], start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flShopcar.removeView(v);
                    }
                }, 300);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }

    private Animation createAnim(int startX, int startY) {
        int[] des = new int[2];
        ivShopcar.getLocationInWindow(des);
        AnimationSet set = new AnimationSet(false);
        Animation translationX = new TranslateAnimation(0, des[0] - startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1] - startY);
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1, 0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y - loc[1]);
        vg.addView(view);
    }

    public void update(int position, String goodsId, String tastyId, boolean isDelete, boolean isInShopCar, int needCount) {
        Log.i(TAG, "update: " + tastyId);
        if (isDelete) {

        } else {
            for (int i = 0; i < needCount; i++) {
                snackFoodsShopCarPresenter.addSnackByUserId((String) SharePreferenceUtil.getUser("uid", "String"), goodsId, tastyId, "0");
            }
        }
        if (isInShopCar) {
            mPresenter.queryUserShopCarAllSnack(String.valueOf(SharePreferenceUtil.getUser("uid", "String")));
        } else {
            if (snackFoodsAdapter != null) {
                snackFoodsAdapter.notifyItemChanged(position);
            }
        }

    }

    @Override
    public void addSnackShopCar(boolean success) {
        if (success) {
            mPresenter.queryUserShopCarAllSnack(String.valueOf(SharePreferenceUtil.getUser("uid", "String")));
            mPresenter.querySnackListByPid(String.valueOf(foodsParentId));

        } else {
            ToastUtils.show("失败了哦！");
        }
//        if (slSort != null)
//            slSort.finishRefresh();
    }
}
