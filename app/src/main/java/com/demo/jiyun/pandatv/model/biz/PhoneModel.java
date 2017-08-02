package com.demo.jiyun.pandatv.model.biz;

import android.os.Bundle;

import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/31.
 */

public interface PhoneModel extends BaseModel{

    void loadImgCode(MyNetWorkCallBack<Bundle> callback);
    void register(String mobileNumber,String phoneCode,String passWd,MyNetWorkCallBack<String> callback);
    void loadPhoneCode(String mobileNumber,String imageCode,String jsonId,MyNetWorkCallBack<String> callback);
}
