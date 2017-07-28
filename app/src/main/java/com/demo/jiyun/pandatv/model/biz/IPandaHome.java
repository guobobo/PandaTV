package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public class IPandaHome implements IPandaHomeModel {
    @Override
    public void loadHomeList(MyNetWorkCallBack<PandaHomeBean> callBack) {
        iHttp.get(Urls.HOMEURL,callBack);
    }
}
