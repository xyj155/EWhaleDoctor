package com.example.module_home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.homefragment.EnergyFragment;
import com.example.module_home.homefragment.HomePageFragment;
import com.example.module_home.homefragment.LeisureFragment;
import com.example.module_home.homefragment.RefreshingPageFragment;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.weight.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RouterConfig.HOMEFRAGMENT)
public class HomeFragment extends BaseFragment<EmptyPresenter> {


    ImageView ivScan;
    ImageView ivSearch;
    MagicIndicator mgTitle;
    ViewPager vpHome;
    Unbinder unbinder;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] title = {"推荐", "提神小件", "休闲食品", "课后零食"};
    private HomePagerAdapter homePagerAdapter;

    @Override
    public void initData() {
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new RefreshingPageFragment());
        fragmentList.add(new LeisureFragment());
        fragmentList.add(new EnergyFragment());
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), fragmentList);
        vpHome.setAdapter(homePagerAdapter);
        mgTitle.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator7 = new CommonNavigator(getContext());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return title == null ? 0 : title.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(title[index]);
                TextPaint tp = simplePagerTitleView.getPaint();
                tp.setFakeBoldText(true);
                simplePagerTitleView.setTextSize(17);
                simplePagerTitleView.setNormalColor(Color.parseColor("#8a8a8a"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#000000"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vpHome.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#FDDF4D"));
                return indicator;
            }
        });
        mgTitle.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mgTitle, vpHome);
    }

    @Override
    public void initView(View view) {
        ivScan = view.findViewById(R.id.iv_scan);
        ivSearch = view.findViewById(R.id.iv_search);
        mgTitle = view.findViewById(R.id.mg_title);
        vpHome = view.findViewById(R.id.vp_home);
    }


    @Override
    public int initLayout() {
        return R.layout.fragment_home;
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

    private class HomePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public HomePagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);

            this.fragmentList = fragmentList;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
