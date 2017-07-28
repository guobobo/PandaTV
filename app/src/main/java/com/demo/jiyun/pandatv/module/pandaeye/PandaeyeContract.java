package com.demo.jiyun.pandatv.module.pandaeye;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.BroadCastBean;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaeyeContract {

    interface View extends BaseView<Presenter> {
        void showPandaeyeData(BroadCastBean broadCastBean);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter {


    }
}
