package com.demo.jiyun.pandatv.module.home;

import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.utils.MyLog;

/**
 * Created by iu on 2017/7/27.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {


    private HomeContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

        presenter.start();
    }

    @Override
    public void showHomeListData(PandaHomeBean pandaHomeBean) {
        MyLog.d("TAG","------------"+pandaHomeBean.getData().getCctv().getTitle());

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

       this.presenter = presenter;
    }
}
