package com.demo.jiyun.pandatv.model.biz;

import android.os.Bundle;

import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.model.entity.NiChengBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/8/2.
 */

public interface LoginModel extends BaseModel{

    void toLogin (String number, String password,MyNetWorkCallBack<Bundle> callback);
    void getName(String name,String cookle,MyNetWorkCallBack<NiChengBean> callBack);
}
