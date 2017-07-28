package com.demo.jiyun.pandatv.module.pandaeye;

import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.BroadCastBean;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaeyeFragment extends BaseFragment implements PandaeyeContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.pandaeye_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showPandaeyeData(BroadCastBean broadCastBean) {

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
    public void setPresenter(PandaeyeContract.Presenter presenter) {

    }
}
