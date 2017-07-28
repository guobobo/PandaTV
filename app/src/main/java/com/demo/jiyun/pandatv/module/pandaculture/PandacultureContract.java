package com.demo.jiyun.pandatv.module.pandaculture;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.CurtureBean;

/**
 * Created by iu on 2017/7/28.
 */

public class PandacultureContract {

    interface View extends BaseView<Presenter> {
        void showPandacultureData(CurtureBean curtureBean);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter {


    }
}
