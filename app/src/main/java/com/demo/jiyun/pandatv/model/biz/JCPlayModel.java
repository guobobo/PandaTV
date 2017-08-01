package com.demo.jiyun.pandatv.model.biz;


import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.model.entity.VoidePlayBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

public interface JCPlayModel extends BaseModel{
    void loadJCPlayData(String id, MyNetWorkCallBack<VoidePlayBean> callBack);
}
