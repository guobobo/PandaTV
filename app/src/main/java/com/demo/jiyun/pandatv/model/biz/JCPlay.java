package com.demo.jiyun.pandatv.model.biz;


import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.VoidePlayBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

public class JCPlay implements JCPlayModel {

    @Override
    public void loadJCPlayData(String id, MyNetWorkCallBack<VoidePlayBean> callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("pid",id);
        iHttp.getWonderfulDta(Urls.VIDEO,map,callBack);
    }

}
