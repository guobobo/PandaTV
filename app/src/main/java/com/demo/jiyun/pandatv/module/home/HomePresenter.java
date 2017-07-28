package com.demo.jiyun.pandatv.module.home;

import com.demo.jiyun.pandatv.model.biz.IPandaHome;
import com.demo.jiyun.pandatv.model.biz.IPandaHomeModel;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public class HomePresenter implements HomeContract.Presenter {


    private HomeContract.View homeView;
    private IPandaHomeModel homeModel;
    public HomePresenter(HomeContract.View homeView){
        this.homeView = homeView;
        homeView.setPresenter(this);
       this.homeModel = new IPandaHome();
    }

    @Override
    public void start() {

        homeView.showProgress();
        homeModel.loadHomeList(new MyNetWorkCallBack<PandaHomeBean>() {


            @Override
            public void onSuccess(PandaHomeBean pandaHomeBean) {
                homeView.showHomeListData(pandaHomeBean);
                homeView.dimissProgress();
            }

            @Override
            public void onError(String message) {

                homeView.showMessage(message);
                homeView.dimissProgress();
            }

        });

    }
}
