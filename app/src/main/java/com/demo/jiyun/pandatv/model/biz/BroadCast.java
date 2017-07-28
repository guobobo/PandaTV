package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.BroadCastBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import static com.demo.jiyun.pandatv.base.BaseModel.iHttp;

/**
 * Created by iu on 2017/7/27.
 */

public class BroadCast implements BroadCastModel {

    @Override
    public void loadBroadCastList(MyNetWorkCallBack<BroadCastBean> callBack) {
        iHttp.get(Urls.BROADCASTURL,callBack);
    }
}
