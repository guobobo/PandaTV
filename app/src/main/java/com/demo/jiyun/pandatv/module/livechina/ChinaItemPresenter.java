package com.demo.jiyun.pandatv.module.livechina;


import com.demo.jiyun.pandatv.model.biz.ChinaLive;
import com.demo.jiyun.pandatv.model.biz.ChinaLiveModel;
import com.demo.jiyun.pandatv.model.entity.ChinaListBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import java.util.ArrayList;

public class ChinaItemPresenter implements ChinaItemContarct.Presenter {
    private ChinaLiveModel chinaModel;
    private ChinaItemContarct.View itemView;

    public ChinaItemPresenter(ChinaItemContarct.View itemView) {
        this.itemView = itemView;
        this.itemView.setPresenter(this);
        chinaModel=new ChinaLive();
    }

    @Override
    public void start() {

    }

    @Override
    public void showLiveChinaItemData(String url) {

        itemView.showProgress();

        chinaModel.loadChinaLiveItemData(url, new MyNetWorkCallBack<ChinaListBean>() {
            @Override
            public void onSuccess(ChinaListBean chinaListBean) {

                ArrayList<ChinaListBean.LiveBean> live = (ArrayList<ChinaListBean.LiveBean>) chinaListBean.getLive();

                itemView.showChinaItemData(live);

                itemView.dimissProgress();
            }

            @Override
            public void onError(String message) {

                itemView.showMessage(message);

                itemView.dimissProgress();
            }
        });


    }
}
