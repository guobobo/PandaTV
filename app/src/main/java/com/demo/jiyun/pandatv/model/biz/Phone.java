package com.demo.jiyun.pandatv.model.biz;

import android.os.Bundle;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.net.OkHttp;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iu on 2017/7/31.
 */

public class Phone implements PhoneModel{

    @Override
    public void loadImgCode(MyNetWorkCallBack<Bundle> callback) {
        OkHttp.getInstance().loadImgCode(Urls.IMGCODE,callback);
    }

    @Override
    public void register(String mobileNumber, String phoneCode, String passWd, MyNetWorkCallBack<String> callback) {

        Map<String ,String> parmes = new HashMap<>();
        parmes.put("method","saveMobileRegisterM");
        parmes.put("mobile",mobileNumber);
        parmes.put("verfiCode",phoneCode);
        try {
            parmes.put("passWd",URLEncoder.encode(passWd, "UTF-8"));
            parmes.put("verfiCodeType","1");
            parmes.put("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String ,String> headers = new HashMap<>();
        try {
            headers.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            headers.put("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        OkHttp.getInstance().loadPhoneCode(Urls.PHONEREGISTER,parmes,headers,callback);

    }


    @Override
    public void loadPhoneCode(String mobileNumber, String imageCode, String jsonId,MyNetWorkCallBack<String> callback) {

        Map<String ,String> parmes = new HashMap<>();

        parmes.put("method","getRequestVerifiCodeM");
        parmes.put("mobile",mobileNumber);
        parmes.put("verfiCodeType","1");
        parmes.put("verificationCode",imageCode);

        Map<String ,String> headers = new HashMap<>();

        try {
            headers.put("Referer",URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));

            headers.put("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            headers.put("Cookie",jsonId);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        OkHttp.getInstance().loadPhoneCode(Urls.PHONECODE,parmes,headers,callback);
    }
}
