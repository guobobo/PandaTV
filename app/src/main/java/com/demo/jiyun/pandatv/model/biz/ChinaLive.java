package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.ChinaListBean;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public class ChinaLive implements ChinaLiveModel {


    @Override
    public void loadChinaLiveList(MyNetWorkCallBack<ChinaLiveBean> callBack) {
        iHttp.get(Urls.CHINAURL,callBack);
    }

    @Override
    public void loadChinaLiveItemData(String url ,MyNetWorkCallBack<ChinaListBean> callBack) {
        iHttp.get(url,callBack);
    }
}
