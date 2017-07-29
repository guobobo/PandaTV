package com.demo.jiyun.pandatv.module.pandalive;

import com.demo.jiyun.pandatv.model.biz.PandaLive;
import com.demo.jiyun.pandatv.model.biz.PandaLiveModel;
import com.demo.jiyun.pandatv.model.entity.LookchatBean;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.model.entity.PandaMultipleBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

public class LiveOnPresenter implements LiveOnContarct.Presenter {
    private PandaLiveModel liveModel ;
    private LiveOnContarct.View liveonView;

    public LiveOnPresenter(LiveOnContarct.View liveonView) {
        this.liveonView = liveonView;
        this.liveonView.setPresenter(this);
        liveModel = new PandaLive();
    }

    @Override
    public void start() {
        liveModel.loadPandaLiveList(new MyNetWorkCallBack<PandaLiveBean>() {
            @Override
            public void onSuccess(PandaLiveBean pandaLiveBean) {
                liveonView.setResult(pandaLiveBean);
            }

            @Override
            public void onError(String msg) {
                liveonView.setMessage(msg);
            }

        });
    }

    @Override
    public void multiple() {
        liveModel.getMultipleData(new MyNetWorkCallBack<PandaMultipleBean>() {
            @Override
            public void onSuccess(PandaMultipleBean multipleBean) {
                liveonView.setMultiple(multipleBean);
            }

            @Override
            public void onError(String msg) {
                liveonView.setMessage(msg);
            }
        });
    }

    @Override
    public void lookchat() {
        liveModel.getLookchatData(new MyNetWorkCallBack<LookchatBean>() {
            @Override
            public void onSuccess(LookchatBean lookchatBean) {
                liveonView.setLookchat(lookchatBean);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
