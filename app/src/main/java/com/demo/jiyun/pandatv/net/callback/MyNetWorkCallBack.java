package com.demo.jiyun.pandatv.net.callback;


public interface MyNetWorkCallBack<T> {

    void onSuccess(T t);
    void onError(String message);

}
