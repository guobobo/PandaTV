package com.demo.jiyun.pandatv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.demo.jiyun.pandatv.app.App;

/**
 * Created by xingge on 2017/7/26.
 */

public class SharedPreferencesManager {


    private static SharedPreferences preferences = App.context.getSharedPreferences("user",Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = preferences.edit();
    public static void saveUserInfo(String key,String userInfo){
        editor.putString(key,userInfo);
        editor.commit();
    }
}
