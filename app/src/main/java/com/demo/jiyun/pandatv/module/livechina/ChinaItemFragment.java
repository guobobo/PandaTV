package com.demo.jiyun.pandatv.module.livechina;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.ChinaListBean;
import com.demo.jiyun.pandatv.utils.ToastManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class ChinaItemFragment extends BaseFragment implements ChinaItemContarct.View {

    @BindView(R.id.chinaitem_recy)
    XRecyclerView chinaitemRecy;
    private ChinaItemAdapter chinaItemAdapter;
    private ChinaItemContarct.Presenter presenter;
    private ArrayList<ChinaListBean.LiveBean> tablistBeen = new ArrayList<>();

    private String url;

    public ChinaItemFragment(String url) {
        this.url = url;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china_item;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
        presenter.showLiveChinaItemData(url);
    }



    @Override
    public void showProgress() {
    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setPresenter(ChinaItemContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showChinaItemData(ArrayList<ChinaListBean.LiveBean> liveBeen) {

        if(liveBeen!=null&&liveBeen.size()>0){

           tablistBeen.clear();
           tablistBeen.addAll(liveBeen);

           LinearLayoutManager manager = new LinearLayoutManager(getContext());
           chinaitemRecy.setLayoutManager(manager);
           chinaItemAdapter=new ChinaItemAdapter(getContext(),tablistBeen);
           chinaitemRecy.setAdapter(chinaItemAdapter);
       }

    }
}
