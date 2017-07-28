package com.demo.jiyun.pandatv.module.livechina;

import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;

/**
 * Created by iu on 2017/7/27.
 */

public class LivechinaFragment extends BaseFragment implements LivechinaContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.livechina_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showLivechinaData(ChinaLiveBean chinaLiveBean) {

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
    public void setPresenter(LivechinaContract.Presenter presenter) {

    }
}
