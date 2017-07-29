package com.demo.jiyun.pandatv.module.pandalive;


import com.demo.jiyun.pandatv.model.biz.PandaLive;
import com.demo.jiyun.pandatv.model.biz.PandaLiveModel;
import com.demo.jiyun.pandatv.model.entity.WonderfulBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;

public class WonderfulPresenter implements WonderfulContarct.Presenter {

    private PandaLiveModel liveModel;
    private WonderfulContarct.View wondrefulView;

    public WonderfulPresenter(WonderfulContarct.View wondrefulView) {
        this.wondrefulView = wondrefulView;
        this.wondrefulView.setPresenter(this);
        liveModel=new PandaLive();
    }

    @Override
    public void start() {

    }

    @Override
    public void Addthenumber(String vsid, String n, String serviceId, String o, String of, String p) {
        liveModel.getWonderfulData(vsid, n, serviceId, o, of, p, new MyNetWorkCallBack<WonderfulBean>() {
            @Override
            public void onSuccess(WonderfulBean wonderfulBean) {

                MyLog.d("TAG",
                        "直播数据请求：："+wonderfulBean.getVideo().get(0).getEm());

                wondrefulView.setResult(wonderfulBean);
            }

            @Override
            public void onError(String msg) {
                wondrefulView.setMessage(msg);
            }
        });
    }
}
