package com.example.module_home.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.module_home.R;
import com.example.module_home.contract.HomePageContract;
import com.example.module_home.presenter.HomePagePresenter;
import com.example.module_library.adapter.SnackMiniItemAdapter;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.gson.FoodsGson;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.GlideUtil;
import com.example.module_library.weight.CustomRecyclerView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomePageContract.View {


    MZBannerView mzbHome;
    Unbinder unbinder;
    private CustomRecyclerView ryPurse;
    private SnackMiniItemAdapter snackMiniItemAdapter;

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("https://img.zcool.cn/community/0130915c11eabda80121ab5dd65553.jpg@1280w_1l_2o_100sh.jpg");
        list.add("https://img.zcool.cn/community/016b5c5c11eabda801209252065a65.jpg@1280w_1l_2o_100sh.jpg");
        list.add("https://img.zcool.cn/community/0114db5c11eabda801209252002209.jpg@1280w_1l_2o_100sh.jpg");
        list.add("https://img.zcool.cn/community/01e1525baaff2ca801213dea2e9a69.jpg@1280w_1l_2o_100sh.jpg");
        mzbHome.setDuration(200);
        mzbHome.setDelayedTime(5000);
        mzbHome.setPages(list, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mzbHome.start();
        mPresenter.test();
        snackMiniItemAdapter = new SnackMiniItemAdapter(null);
        ryPurse.setAdapter(snackMiniItemAdapter);
    }

    @Override
    public void test(List<FoodsGson> list) {

        snackMiniItemAdapter.replaceData(list);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            GlideUtil.BaseGlide(data, mImageView);
        }
    }

    @Override
    public void initView(View view) {
        mzbHome = view.findViewById(R.id.mzb_home);
        ryPurse = view.findViewById(R.id.ry_purse);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        ryPurse.setLayoutManager(layout);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    public HomePagePresenter initPresenter() {
        return new HomePagePresenter(this);
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