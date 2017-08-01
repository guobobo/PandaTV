package com.demo.jiyun.pandatv.model.biz;


import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.model.entity.InteractivesBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

public interface InteracitveModel extends BaseModel{
    void loadInteracitveList(MyNetWorkCallBack<InteractivesBean> callBack);
}
