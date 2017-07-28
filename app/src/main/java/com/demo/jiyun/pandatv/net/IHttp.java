package com.demo.jiyun.pandatv.net;


import android.widget.ImageView;

import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

import java.util.Map;

public interface IHttp {
    <T> void get(String url, MyNetWorkCallBack<T> callBack);
    <T> void get(String url, Map<String,String> params,MyNetWorkCallBack<T> callBack);
    <T> void get(String url,Map<String,String> params,Map<String,String> heads,MyNetWorkCallBack<T> callBack);
    <T> void post(String url, Map<String,String> params,MyNetWorkCallBack<T> callBack);
    <T> void post(String url,Map<String,String> params,Map<String,String> heads,MyNetWorkCallBack<T> callBack);
    void upLoad();
    void downLoad();
    void imageLoad(String url , ImageView imageView);
}
