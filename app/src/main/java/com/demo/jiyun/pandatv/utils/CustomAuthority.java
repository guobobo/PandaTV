package com.demo.jiyun.pandatv.utils;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.demo.jiyun.pandatv.app.App;

/**
 * Created by iu on 2017/7/28.
 */

public class CustomAuthority {

    public CustomAuthority() {
    }

    public static void Addto(){

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(App.context, mPermissionList, 123);
        }
    }

}
