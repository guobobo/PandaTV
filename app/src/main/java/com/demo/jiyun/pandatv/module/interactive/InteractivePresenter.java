package com.demo.jiyun.pandatv.module.interactive;


import com.demo.jiyun.pandatv.model.biz.Interacitve;
import com.demo.jiyun.pandatv.model.biz.InteracitveModel;
import com.demo.jiyun.pandatv.model.entity.InteractivesBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

public class InteractivePresenter implements InteractiveContarct.Presenter {

    private InteractiveContarct.View interView;
    private InteracitveModel interacitveModel;

    public InteractivePresenter(InteractiveContarct.View interView) {
        this.interView = interView;
        interView.setPresenter(this);
        this.interacitveModel=new Interacitve();
    }

    @Override
    public void start() {
        interView.showProgress();
        interacitveModel.loadInteracitveList(new MyNetWorkCallBack<InteractivesBean>() {
            @Override
            public void onSuccess(InteractivesBean interactivesBean) {

                interView.showInteracitveData(interactivesBean);
                interView.dimissProgress();
            }

            @Override
            public void onError(String message) {
                interView.dimissProgress();
                interView.showMessage(message);
            }
        });
    }
}
