package com.demo.jiyun.pandatv.module.play;


import com.demo.jiyun.pandatv.model.biz.JCPlay;
import com.demo.jiyun.pandatv.model.biz.JCPlayModel;
import com.demo.jiyun.pandatv.model.entity.VoidePlayBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

public class JCPlayPresenter implements JCPlayContarct.Presenter{

    private JCPlayModel jcPlayModel;
    private JCPlayContarct.View jcView;

    public JCPlayPresenter(JCPlayContarct.View jcView) {
        this.jcView = jcView;
        jcView.setPresenter(this);
        this.jcPlayModel=new JCPlay();
    }

    @Override
    public void showPlay(String id) {
        jcPlayModel.loadJCPlayData(id, new MyNetWorkCallBack<VoidePlayBean>() {
            @Override
            public void onSuccess(VoidePlayBean voidePlayBean) {
                jcView.getPlayUrl(voidePlayBean);
            }

            @Override
            public void onError(String message) {
                jcView.showMessage(message);
            }
        });
    }

    @Override
    public void start() {

    }



}
