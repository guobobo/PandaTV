package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.LookchatBean;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.model.entity.PandaMultipleBean;
import com.demo.jiyun.pandatv.model.entity.WonderfulBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iu on 2017/7/27.
 */

public class PandaLive implements PandaLiveModel {



    @Override
    public void loadPandaLiveList(MyNetWorkCallBack<PandaLiveBean> callBack) {
        iHttp.get(Urls.LIVEURL,callBack);
    }

    @Override
    public void getWonderfulData(String vsid, String n, String serviceId, String o, String of, String p, MyNetWorkCallBack<WonderfulBean> callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("vsid",vsid);
        map.put("n",n);
        map.put("serviceId",serviceId);
        map.put("o",o);
        map.put("of",of);
        map.put("p",p);
        iHttp.getWonderfulDta(Urls.Wonderful,map,callBack);
    }

    @Override
    public void getMultipleData(MyNetWorkCallBack<PandaMultipleBean> callBack) {
        iHttp.get(Urls.MULTIPLE,callBack);
    }

    @Override
    public void getLookchatData(MyNetWorkCallBack<LookchatBean> callBack) {
        iHttp.get(Urls.Lookchar,callBack);
    }
}
