package com.demo.jiyun.pandatv.module.interactive;


import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.InteractivesBean;

public class InteractiveContarct {

    interface Presenter extends BasePresenter{
    }

    interface View extends BaseView<Presenter>{
        void showInteracitveData(InteractivesBean interactivesBeanList);
    }

}
