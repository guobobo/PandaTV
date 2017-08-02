package com.demo.jiyun.pandatv.model.biz;

import android.os.Bundle;

import com.demo.jiyun.pandatv.model.entity.NiChengBean;
import com.demo.jiyun.pandatv.net.OkHttp;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iu on 2017/8/2.
 */

public class Login implements LoginModel {
    @Override
    public void toLogin(String number, String password, MyNetWorkCallBack<Bundle> callback) {
        MyLog.d("LoginPresenter",number+"++++++++++"+password);
        Map<String ,String> headers = new HashMap<>();

        try {
            String from = "https://reg.cntv.cn/login/login.action";

            headers.put("Referer", URLEncoder.encode(from, "UTF-8"));

            headers.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"));

            String url = from + "?username="
                    + URLEncoder.encode(number, "UTF-8")
                    + "&password=" + password
                    + "&service=client_transaction" + "&from="
                    + URLEncoder.encode(from, "UTF-8");

            OkHttp.getInstance().getLoginCookie(url,headers,callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void getName(String name,String cookie, MyNetWorkCallBack<NiChengBean> callBack) {

        Map<String ,String> headers = new HashMap<>();

        String form = "http://cbox_mobile.regclientuser.cntv.cn";
        String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                + "cbox_mobile" + "&method=" + "user.getNickName"
                + "&userid=" + name;

        try {
            headers.put("Referer",URLEncoder.encode(form, "UTF-8"));
            headers.put("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            headers.put("Cookie",cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        iHttp.getHeaders(url,headers,callBack);




    }
}
