package com.demo.jiyun.pandatv.model.biz;


import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.InteractivesBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

public class Interacitve implements InteracitveModel {

    @Override
    public void loadInteracitveList(MyNetWorkCallBack<InteractivesBean> callBack) {
        iHttp.get(Urls.INTERACTIVE,callBack);
    }
}
