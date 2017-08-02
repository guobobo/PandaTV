package com.demo.jiyun.pandatv.app;


import android.app.Application;

import com.demo.jiyun.pandatv.base.BaseActivity;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class App extends Application {

    public static BaseActivity context=null;
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106321830", "nGuCvB0IFvIKz4lW");
        PlatformConfig.setSinaWeibo("2853957639", "be66045c566b3739629f7ead39cea781", "http://sina.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        UMShareAPI.get(this);

        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
//                Log.e("TAG","deviceToken:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

        //全局捕获异常
//        CrashHandler instance = CrashHandler.getInstance();
//        instance.init(getApplicationContext());

    }

}
