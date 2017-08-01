package com.demo.jiyun.pandatv.module.play;


import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.VoidePlayBean;

public class JCPlayContarct {
    interface Presenter extends BasePresenter{
        void showPlay(String id);
    }

    interface View extends BaseView<Presenter>{
        void getPlayUrl(VoidePlayBean playBean);
    }
}
