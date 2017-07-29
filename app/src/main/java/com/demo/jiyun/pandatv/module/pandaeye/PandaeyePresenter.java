package com.demo.jiyun.pandatv.module.pandaeye;

import com.demo.jiyun.pandatv.model.biz.BroadCast;
import com.demo.jiyun.pandatv.model.biz.BroadCastModel;
import com.demo.jiyun.pandatv.model.entity.BroadCastBean;
import com.demo.jiyun.pandatv.model.entity.BroadCastListBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaeyePresenter implements PandaeyeContract.Presenter {

    private PandaeyeContract.View pandaeyeview;
    private BroadCastModel broadCastModel;
    public PandaeyePresenter(PandaeyeContract.View pandaeyeview){
        this.pandaeyeview = pandaeyeview;
        pandaeyeview.setPresenter(this);
        this.broadCastModel = new BroadCast();
    }

    @Override
    public void start() {
        pandaeyeview.showProgress();
        broadCastModel.loadBroadCastList(new MyNetWorkCallBack<BroadCastBean>() {
            @Override
            public void onSuccess(BroadCastBean broadCastBean) {
                pandaeyeview.showPandaeyeData(broadCastBean);
                pandaeyeview.dimissProgress();
            }
            @Override
            public void onError(String message) {
                pandaeyeview.showMessage(message);
                pandaeyeview.dimissProgress();
            }
        });
        broadCastModel.LoadBroadCastUrlListData(new MyNetWorkCallBack<BroadCastListBean>() {
            @Override
            public void onSuccess(BroadCastListBean broadCastListBean) {
                pandaeyeview.showPandaeyeListData(broadCastListBean);
                pandaeyeview.dimissProgress();
            }

            @Override
            public void onError(String message) {
                pandaeyeview.showMessage(message);
                pandaeyeview.dimissProgress();
            }
        });
        
    }
}

