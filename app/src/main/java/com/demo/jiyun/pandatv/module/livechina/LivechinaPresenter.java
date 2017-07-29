package com.demo.jiyun.pandatv.module.livechina;

import com.demo.jiyun.pandatv.model.biz.ChinaLive;
import com.demo.jiyun.pandatv.model.biz.ChinaLiveModel;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public class LivechinaPresenter implements LivechinaContract.Presenter {


    private LivechinaContract.View livechainaView;
    private ChinaLiveModel homeModel;
    public LivechinaPresenter(LivechinaContract.View livechainaView){
        this.livechainaView = livechainaView;
        livechainaView.setPresenter(this);
        this.homeModel = new ChinaLive();
    }

    @Override
    public void start() {

        livechainaView.showProgress();
        homeModel.loadChinaLiveList(new MyNetWorkCallBack<ChinaLiveBean>() {
            @Override
            public void onSuccess(ChinaLiveBean chinaLiveBean) {

                livechainaView.showLivechinaData(chinaLiveBean);

                livechainaView.dimissProgress();
            }

            @Override
            public void onError(String message) {

                livechainaView.showMessage(message);
                livechainaView.dimissProgress();
            }

        });

    }

}
