package com.demo.jiyun.pandatv.module.pandalive;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaliveContract {

    interface View extends BaseView<Presenter> {
        void showPandaeyeData(PandaLiveBean pandaLiveBean);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter {


    }
}
