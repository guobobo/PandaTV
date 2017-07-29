package com.demo.jiyun.pandatv.module.pandalive;


import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.LookchatBean;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.model.entity.PandaMultipleBean;

public interface LiveOnContarct {
    interface Presenter extends BasePresenter {
        void multiple();
        void lookchat();
    }
    interface View extends BaseView<Presenter> {
        void setMultiple(PandaMultipleBean pandaMultipleBean);
        void setResult(PandaLiveBean pandaLiveBean);
        void setMessage(String msg);
        void setLookchat(LookchatBean lookchatBean);
    }

}
