package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public class IPandaHome implements IPandaHomeModel {
    @Override
    public void loadHomeList(MyNetWorkCallBack<PandaHomeBean> callBack) {
        iHttp.get(Urls.HOMEURL,callBack);
    }

    @Override
    public void loadPandaeyeData(String url, MyNetWorkCallBack<PandaeyeListBean> callBack) {
        iHttp.get(url,callBack);
    }

    @Override
    public void loadChinaLightData(String url, MyNetWorkCallBack<HomelightListBean> callBack) {
        iHttp.get(url,callBack);
    }

    @Override
    public void loadCCTVData(String url, MyNetWorkCallBack<HomecctvListBean> callBack) {
        iHttp.get(url,callBack);
    }

    @Override
    public void version(MyNetWorkCallBack<UpdateBean> callBack) {
        iHttp.get(Urls.UPDATE_URL,null,callBack);
    }
}
