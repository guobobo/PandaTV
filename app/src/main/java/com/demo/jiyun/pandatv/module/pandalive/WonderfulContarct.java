package com.demo.jiyun.pandatv.module.pandalive;


import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.WonderfulBean;

public interface WonderfulContarct {
    interface Presenter extends BasePresenter {
        void Addthenumber(String vsid, String n, String serviceId, String o, String of, String p);
    }
    interface View extends BaseView<Presenter> {

        void setResult(WonderfulBean wonderfulBean);
        void setMessage(String msg);
    }
}
