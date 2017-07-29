package com.demo.jiyun.pandatv.utils;


import android.content.Intent;

import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.module.play.JCPlayActivity;
import com.demo.jiyun.pandatv.module.web.WebActivity;

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

}
