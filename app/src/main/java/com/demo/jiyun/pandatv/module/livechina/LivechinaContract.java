package com.demo.jiyun.pandatv.module.livechina;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;

/**
 * Created by iu on 2017/7/27.
 */

public class LivechinaContract {

    interface View extends BaseView<Presenter> {
        void showLivechinaData(ChinaLiveBean chinaLiveBean);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter {


    }
}
