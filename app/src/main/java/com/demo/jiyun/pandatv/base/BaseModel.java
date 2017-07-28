package com.demo.jiyun.pandatv.base;


import com.demo.jiyun.pandatv.net.HttpFactory;
import com.demo.jiyun.pandatv.net.IHttp;

public interface BaseModel {
    public static IHttp iHttp = HttpFactory.httpCreate();
}
