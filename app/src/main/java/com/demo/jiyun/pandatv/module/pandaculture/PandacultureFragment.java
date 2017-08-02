package com.demo.jiyun.pandatv.module.pandaculture;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.CurtureBean;
import com.demo.jiyun.pandatv.module.pandaculture.adapter.PandacultureAapterXRecy;
import com.demo.jiyun.pandatv.utils.ACache;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.ToastManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by iu on 2017/7/27.
 */

public class PandacultureFragment extends BaseFragment implements PandacultureContract.View,XRecyclerView.LoadingListener {

    @BindView(R.id.pandaculture_xrecy)
    XRecyclerView pandacultureXrecy;
    private PandacultureContract.Presenter presenter;
    private PandacultureAapterXRecy pandacultureAapterXRecy;
    private List<Object> datas;
    @Override
    protected int getLayoutId() {
        return R.layout.pandaculture_fragment;
    }

    @Override
    protected void init(View view) {
        new PandaculturePresenter(this);
        datas=new ArrayList<Object>();

        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        pandacultureXrecy.setLayoutManager(manager);
        pandacultureXrecy.setPullRefreshEnabled(true);
        pandacultureXrecy.setLoadingMoreEnabled(false);
        pandacultureXrecy.setLoadingListener(this);
        pandacultureAapterXRecy=new PandacultureAapterXRecy(getActivity(),datas);
        pandacultureXrecy.setAdapter(pandacultureAapterXRecy);
    }

    @Override
    protected void loadData() {
        presenter.start();
    }

    @Override
    public void showPandacultureData(CurtureBean curtureBean) {
        datas.clear();
        datas.add(curtureBean.getBigImg());
        datas.add(curtureBean.getList());
        pandacultureAapterXRecy.notifyDataSetChanged();
        pandacultureXrecy.refreshComplete();
        pandacultureXrecy.loadMoreComplete();
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

        ACache aCache = ACache.get(getContext());
        CurtureBean curtureBean = (CurtureBean) aCache.getAsObject("CurtureBean");
        if(curtureBean!=null){
            datas.clear();
            datas.add(curtureBean.getBigImg());
            datas.add(curtureBean.getList());
            pandacultureAapterXRecy.notifyDataSetChanged();
            pandacultureXrecy.refreshComplete();
            pandacultureXrecy.loadMoreComplete();
        }else {
            ToastManager.show(msg);
        }
    }

    @Override
    public void setPresenter(PandacultureContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick(R.id.pandaculture_xrecy)
    public void onViewClicked() {

    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {
        ToastManager.show("没有更多数据了");
    }
}
