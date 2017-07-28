package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import static com.demo.jiyun.pandatv.base.BaseModel.iHttp;

/**
 * Created by iu on 2017/7/27.
 */

public class PandaLive implements PandaLiveModel {



    @Override
    public void loadPandaLiveList(MyNetWorkCallBack<PandaLiveBean> callBack) {
        iHttp.get(Urls.LIVEURL,callBack);
    }
}
