package com.demo.jiyun.pandatv.module.home;

import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.model.biz.IPandaHome;
import com.demo.jiyun.pandatv.model.biz.IPandaHomeModel;
import com.demo.jiyun.pandatv.model.biz.UpdateBean;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;

import java.util.List;

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
            public void onSuccess(final PandaHomeBean pandaHomeBean) {
                String eyeUrl = pandaHomeBean.getData().getPandaeye().getPandaeyelist();
                List<PandaHomeBean.DataBean.ListBeanXXX> list = pandaHomeBean.getData().getList();
                String cctvUrl = pandaHomeBean.getData().getCctv().getListurl();
                MyLog.d("TAG","eye接口：："+eyeUrl);
                homeView.showHomeListData(pandaHomeBean);
                getPandaeyeData(eyeUrl);
                for(int i = 0; i < list.size(); i++) {
                    PandaHomeBean.DataBean.ListBeanXXX listBeanXXX = list.get(i);
                    String lightUrl = listBeanXXX.getListUrl();
                    getChinaLight(lightUrl);
                }
                getCCTVData(cctvUrl);
                homeView.dimissProgress();
            }
            @Override
            public void onError(String message) {

                homeView.showMessage(message);
                homeView.dimissProgress();
            }

        });
    }

    @Override
    public void getPandaeyeData(String url) {
        homeModel.loadPandaeyeData(url, new MyNetWorkCallBack<PandaeyeListBean>() {
            @Override
            public void onSuccess(PandaeyeListBean pandaeyeListBean) {
                homeView.showPandaeyeData(pandaeyeListBean);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void getChinaLight(String url) {

        homeModel.loadChinaLightData(url, new MyNetWorkCallBack<HomelightListBean>() {
            @Override
            public void onSuccess(HomelightListBean homelightListBean) {
                homeView.showChinaLightData(homelightListBean);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void getCCTVData(String url) {
        homeModel.loadCCTVData(url, new MyNetWorkCallBack<HomecctvListBean>() {
            @Override
            public void onSuccess(HomecctvListBean homecctvListBean) {
                homeView.showCCTVData(homecctvListBean);
            }

            @Override
            public void onError(String message) {
            }
        });
    }

    @Override
    public void getversion() {

        homeModel.version(new MyNetWorkCallBack<UpdateBean>() {
            @Override
            public void onSuccess(final UpdateBean updateBean) {
                //成功的回调
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeView.getVersionData(updateBean);
                        MyLog.d("版本更新bean打印",updateBean.getData().getVersionsinfo().toString());

                    }
                });
            }

            @Override
            public void onError(String msg) {
                MyLog.d("版本更新网络请求错误",msg);
            }

        });

    }
}
