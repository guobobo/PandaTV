package com.demo.jiyun.pandatv.module.pandalive;

import com.demo.jiyun.pandatv.model.biz.PandaLive;
import com.demo.jiyun.pandatv.model.biz.PandaLiveModel;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/28.
 */

public class PandalivePresenter implements PandaliveContract.Presenter {

    private PandaliveContract.View pandaliveview;
    private PandaLiveModel pandaLiveModel;
    public PandalivePresenter(PandaliveContract.View pandaliveview){
        this.pandaliveview = pandaliveview;
        pandaliveview.setPresenter(this);
        this.pandaLiveModel = new PandaLive();
    }

    @Override
    public void start() {

        pandaliveview.showProgress();
        pandaLiveModel.loadPandaLiveList(new MyNetWorkCallBack<PandaLiveBean>() {





            @Override
            public void onSuccess(PandaLiveBean pandaLiveBean) {
                pandaliveview.showPandaeyeData(pandaLiveBean);
                pandaliveview.dimissProgress();
            }

            @Override
            public void onError(String message) {

                pandaliveview.showMessage(message);
                pandaliveview.dimissProgress();
            }

        });

    }
}
