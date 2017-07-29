package com.demo.jiyun.pandatv.module.pandaculture;

import com.demo.jiyun.pandatv.model.biz.Curture;
import com.demo.jiyun.pandatv.model.biz.CurtureModel;
import com.demo.jiyun.pandatv.model.entity.CurtureBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaculturePresenter implements PandacultureContract.Presenter {

    private PandacultureContract.View pandaultureview;
    private CurtureModel homeModel;
    public PandaculturePresenter(PandacultureContract.View pandaultureview){
        this.pandaultureview = pandaultureview;
        pandaultureview.setPresenter(this);
        this.homeModel = new Curture();
    }

    @Override
    public void start() {

        pandaultureview.showProgress();
        homeModel.loadCurtureBeanList(new MyNetWorkCallBack<CurtureBean>() {
            @Override
            public void onSuccess(CurtureBean curtureBean) {
                pandaultureview.showPandacultureData(curtureBean);
                pandaultureview.dimissProgress();
            }

            @Override
            public void onError(String message) {

                pandaultureview.showMessage(message);
                pandaultureview.dimissProgress();
            }
        });

    }
}
