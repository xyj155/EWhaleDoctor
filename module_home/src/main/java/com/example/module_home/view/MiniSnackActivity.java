package com.example.module_home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.module_home.homefragment.LeisureFragment;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.weight.ViewPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MiniSnackActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {


    @BindView(R2.id.test)
    ViewPageIndicator test;
    @BindView(R2.id.vp_test)
    ViewPager vpTest;

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
        return R.layout.activity_mini_snack;
    }

    @Override
    public void initView() {
        initToolBar().setToolBarTitle("Mini零食");

    }

    @Override
    public void initData() {
        ButterKnife.bind(this);
        String[] titles = new String[]{"面包糕点", "曲奇饼干", "风味小吃", "坚果蜜饯", "固体冲饮", "薯片膨化", "即食海鲜", "网红食品"};
        List<String> imageList = new ArrayList<>();
        imageList.add("https://img.alicdn.com/imgextra/i2/725677994/TB2JVe9r_qWBKNjSZFAXXanSpXa_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i3/725677994/TB2dqB3EnlYBeNjSszcXXbwhFXa_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i4/725677994/TB2XjIzBh9YBuNjy0FfXXXIsVXa_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i4/725677994/O1CN012qeUQ428vIdvdrfAy_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i2/725677994/O1CN0128vIclDPym0rhzo_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i2/725677994/TB2zufafgmTBuNjy1XbXXaMrVXa_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i4/725677994/O1CN01rwJQya28vIdRkzQb3_!!725677994.jpg_430x430q90.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i3/725677994/TB26FUVhnXYBeNkHFrdXXciuVXa_!!725677994.jpg_430x430q90.jpg");
        test.setTitles(titles,imageList);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        fragments.add(new LeisureFragment());
        Test test1 = new Test(getSupportFragmentManager(), fragments);
        vpTest.setAdapter(test1);
        test.setViewPager(vpTest);

    }

    private class Test extends FragmentPagerAdapter {
        private List<Fragment> strings;

        public Test(FragmentManager fm, List<Fragment> strings) {
            super(fm);
            this.strings = strings;
        }

        @Override
        public Fragment getItem(int position) {
            return strings.get(position);
        }

        @Override
        public int getCount() {
            return strings.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
