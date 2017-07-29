package com.demo.jiyun.pandatv.module.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.module.home.adapter.HomeAdapter;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by iu on 2017/7/27.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {


    @BindView(R.id.home_xrecy)
    XRecyclerView homeXrecy;
    Unbinder unbinder;
    private List<Object> datas;
    private HomeContract.Presenter presenter;
    private HomeAdapter homeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init(View view) {

        this.datas = new ArrayList<Object>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        homeXrecy.setLayoutManager(manager);
        homeXrecy.setPullRefreshEnabled(true);
        homeXrecy.setLoadingMoreEnabled(false);
        homeAdapter = new HomeAdapter(datas,getActivity());
        homeXrecy.setAdapter(homeAdapter);

    }

    @Override
    protected void loadData() {

        presenter.start();
    }

    @Override
    public void showHomeListData(PandaHomeBean pandaHomeBean) {

        datas.clear();
        datas.add(pandaHomeBean.getData().getBigImg());
        datas.add(pandaHomeBean.getData().getArea());
        datas.add(pandaHomeBean.getData().getPandaeye());
        datas.add(pandaHomeBean.getData().getPandalive());
        datas.add(pandaHomeBean.getData().getWalllive());
        datas.add(pandaHomeBean.getData().getChinalive());
        datas.add(pandaHomeBean.getData().getInteractive());
        datas.add(pandaHomeBean.getData().getCctv());
        datas.add(pandaHomeBean.getData().getList());
        homeAdapter.notifyDataSetChanged();
        homeXrecy.refreshComplete();
        homeXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                presenter.start();
            }

            @Override
            public void onLoadMore() {

            }
        });



    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    @Override
    public void showProgress() {

        CustomDialog.show(getActivity());
    }

    @Override
    public void dimissProgress() {
        CustomDialog.dimiss();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

        this.presenter = presenter;
    }



}
