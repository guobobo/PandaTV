package com.demo.jiyun.pandatv.utils;

import android.widget.Toast;

import com.demo.jiyun.pandatv.app.App;

/**
 * Created by xingge on 2017/7/26.
 */

public class ToastManager {

    public static void show(String msg){
        Toast.makeText(App.context,msg,Toast.LENGTH_LONG).show();
    }
}
