package com.demo.jiyun.pandatv.module.home;

import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;

/**
 * Created by iu on 2017/7/27.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showHomeListData(PandaHomeBean pandaHomeBean) {

    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }
}
