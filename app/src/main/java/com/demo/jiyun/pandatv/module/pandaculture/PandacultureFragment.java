package com.demo.jiyun.pandatv.module.pandaculture;

import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.CurtureBean;

/**
 * Created by iu on 2017/7/27.
 */

public class PandacultureFragment extends BaseFragment implements PandacultureContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.pandaculture_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showPandacultureData(CurtureBean curtureBean) {

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
    public void setPresenter(PandacultureContract.Presenter presenter) {

    }
}
