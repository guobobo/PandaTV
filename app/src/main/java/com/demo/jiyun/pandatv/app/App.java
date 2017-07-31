package com.demo.jiyun.pandatv.app;


import android.app.Application;

import com.demo.jiyun.pandatv.base.BaseActivity;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class App extends Application {

    public static BaseActivity context=null;
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("2853957639", "be66045c566b3739629f7ead39cea781", "http://sina.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        UMShareAPI.get(this);
    }

}
