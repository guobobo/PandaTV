package com.demo.jiyun.pandatv.net;


public class HttpFactory {

    public static IHttp httpCreate(){
        return OkHttp.getInstance();
    }

}
