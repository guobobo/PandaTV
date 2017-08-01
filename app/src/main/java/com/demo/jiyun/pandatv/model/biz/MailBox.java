package com.demo.jiyun.pandatv.model.biz;

import android.os.Bundle;

import com.demo.jiyun.pandatv.config.Urls;
import com.demo.jiyun.pandatv.model.entity.RegisterBean;
import com.demo.jiyun.pandatv.net.OkHttp;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iu on 2017/7/31.
 */

public class MailBox  implements MailBoxModel{
    @Override
    public void loadImgCode(MyNetWorkCallBack<Bundle> callback) {

        OkHttp.getInstance().loadImgCode(Urls.IMGCODE,callback);

    }

    @Override
    public void register(String mailAdd, String passWd, String verificationCode, String cookie, MyNetWorkCallBack<RegisterBean> callback) {

        String addons = null;
        String userAgent = null;
        try {
            addons = URLEncoder.encode("iPanda.Android", "UTF-8");
            userAgent = URLEncoder.encode("CNTV_APP_CLIENT_CNTV_MOBILE", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> params = new HashMap<>();
        params.put("mailAdd",mailAdd);
        params.put("passWd",passWd);
        params.put("verificationCode",verificationCode);
        params.put("addons", addons);

        Map<String,String> headers = new HashMap<>();
        headers.put("Referer",addons);
        headers.put("User-Agent",userAgent);
        headers.put("Cookie",cookie);

        iHttp.getReisteredData(Urls.EMAILREGISTER,params,headers,callback);
    }
}
