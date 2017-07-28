package com.demo.jiyun.pandatv.module.home;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;

/**
 * Created by iu on 2017/7/27.
 */

public class HomeContract {

    interface View extends BaseView<Presenter> {
        void showHomeListData(PandaHomeBean pandaHomeBean);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter {


    }

}
