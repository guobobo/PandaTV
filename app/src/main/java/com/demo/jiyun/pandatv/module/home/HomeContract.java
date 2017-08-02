package com.demo.jiyun.pandatv.module.home;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.biz.UpdateBean;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;

/**
 * Created by iu on 2017/7/27.
 */

public class HomeContract {

    interface View extends BaseView<Presenter> {
        void showHomeListData(PandaHomeBean pandaHomeBean);
        void showPandaeyeData(PandaeyeListBean pandaeyeListBean);
        void showChinaLightData(HomelightListBean homelightListBean);
        void showCCTVData(HomecctvListBean homecctvListBean);
        void getVersionData(UpdateBean updateBean);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter {
        void getPandaeyeData(String url);
        void getChinaLight(String url);
        void getCCTVData(String url);
        void getversion();
    }

}
