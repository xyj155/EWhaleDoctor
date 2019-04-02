package com.example.module_kind.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_kind.R;
import com.example.module_kind.adapter.SnackKindAdapter;
import com.example.module_kind.contract.SnackKindContract;
import com.example.module_kind.presenter.SnackKindPresenter;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.config.RouterConfig;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import nico.stytool.gson_module.SnackKindGson;

@Route(path = RouterConfig.KINDFRAGMENT)
public class KindFragment extends BaseFragment<SnackKindPresenter> implements SnackKindContract.View {


    RecyclerView ryKind;
    SwipeRefreshLayout smlKind;
    Unbinder unbinder;
    private SnackKindAdapter snackKindAdater;

    @Override
    public void initData() {
        snackKindAdater = new SnackKindAdapter(null,getContext());
        ryKind.setLayoutManager(new GridLayoutManager(getContext(),2));
        ryKind.setAdapter(snackKindAdater);
        mPresenter.querySnackKind();
        smlKind.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.querySnackKind();
            }
        });
    }

    @Override
    public void initView(View view) {
        ryKind = view.findViewById(R.id.ry_kind);
        smlKind = view.findViewById(R.id.sml_kind);
    }


    @Override
    public int initLayout() {
        return R.layout.fragment_kind;
    }

    @Override
    public SnackKindPresenter initPresenter() {
        return new SnackKindPresenter(this);
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

    @Override
    public void querySnackKind(List<SnackKindGson> snackKindGsons) {
        snackKindAdater.replaceData(snackKindGsons);
        smlKind.setRefreshing(false);
    }

    @Override
    public void showError(String msg) {
        smlKind.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        createDialog();
    }

    @Override
    public void hideDialog() {
dialogCancel();
    }
}
