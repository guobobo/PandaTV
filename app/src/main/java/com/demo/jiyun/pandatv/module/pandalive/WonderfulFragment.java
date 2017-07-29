package com.demo.jiyun.pandatv.module.pandalive;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.WonderfulBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;


public class WonderfulFragment extends BaseFragment implements WonderfulContarct.View {

    @BindView(R.id.wond_xrecy)
    XRecyclerView wondXrecy;
    private WonderfulContarct.Presenter presenter;
    private String VSID;
    private static String n = "7";
    private static final String serviceId = "panda";
    private static final String o = "desc";
    private static final String of = "time";
    private static int p = 1;
    private ArrayList<WonderfulBean.VideoBean> list;
    private WonderfulAdapter wonderfulAdapter;

    public WonderfulFragment(String VSID) {
        this.VSID = VSID;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wonderful;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

        presenter.start();
        presenter.Addthenumber(VSID, n, serviceId, o, of, p + "");

    }

    @Override
    public void setResult(WonderfulBean wonderfulBean) {

        list = new ArrayList<>();
        for (int i = 0; i < wonderfulBean.getVideo().size(); i++) {
            WonderfulBean.VideoBean videoBean = wonderfulBean.getVideo().get(i);
            list.add(videoBean);
        }
        wonderfulAdapter = new WonderfulAdapter(getContext(), list);

        wondXrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        wondXrecy.setAdapter(wonderfulAdapter);
        wondXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                p = 1;
                presenter.Addthenumber(VSID, n, serviceId, o, of, p + "");
                wondXrecy.refreshComplete();
                wonderfulAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        p++;
                    }
                }, 1000);
                presenter.Addthenumber(VSID, n, serviceId, o, of, p + "");
                wondXrecy.loadMoreComplete();
                wonderfulAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setMessage(String msg) {

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
    public void setPresenter(WonderfulContarct.Presenter presenter) {
        this.presenter = presenter;
    }
}
