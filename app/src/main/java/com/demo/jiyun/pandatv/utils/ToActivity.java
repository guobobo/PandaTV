package com.demo.jiyun.pandatv.utils;


import android.content.Intent;

import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.module.play.JCPlayActivity;
import com.demo.jiyun.pandatv.module.web.WebActivity;
import com.demo.jiyun.pandatv.module.web.WebInteractiveActivity;

public class ToActivity {
    public static void loadPlay(String id,String title,String image,String videolength){
        Intent intent=new Intent(App.context,JCPlayActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("image",image);
        intent.putExtra("duration",videolength);
        intent.putExtra("id",id);
        App.context.startActivity(intent);
    }

    public static void loadWeb(String url){
        Intent intent=new Intent(App.context,WebActivity.class);
        intent.putExtra("url",url);
        App.context.startActivity(intent);
    }

    public static void loadInteractive(String url,String title){
        Intent intent=new Intent(App.context, WebInteractiveActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        App.context.startActivity(intent);
    }

    public static void load(Class<? extends BaseActivity> baseActivity){
        Intent intent=new Intent(App.context,baseActivity);
        App.context.startActivity(intent);
    }

}
