package com.demo.jiyun.pandatv.utils;

import android.util.Log;


public class MyLog {
    private static boolean isOpen = true;
    public static void d(String tag,String msg){
        if(isOpen)
            Log.d(tag,msg);
    }
}
