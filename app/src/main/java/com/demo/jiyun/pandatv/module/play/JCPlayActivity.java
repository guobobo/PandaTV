package com.demo.jiyun.pandatv.module.play;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.model.db.Keep;
import com.demo.jiyun.pandatv.model.entity.VoidePlayBean;
import com.demo.jiyun.pandatv.utils.DBUtils;
import com.demo.jiyun.pandatv.utils.ToastManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JCPlayActivity extends BaseActivity implements JCPlayContarct.View,JCVideoPlayerStandards.MyOnClikListener{

    private String title, data, duration, url, image,id;
    private JCPlayContarct.Presenter presenter;
    private String video_url;
    private String video_url1;
    private String SPURL = "";
    final int HANDLERMSG = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLERMSG:
                    if(SPURL.equals("")) {
                        SPURL=video_url;
                    }

                    initView();
                    break;
            }
        }
    };
    private DBUtils instance;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jcplay;
    }

    @Override
    protected void init() {
        new JCPlayPresenter(this);
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("image");
        duration = getIntent().getStringExtra("duration");

        presenter.showPlay(id);
        instance = DBUtils.getInstance(this);
    }

    private void initView() {
        JCVideoPlayerStandards myjcv =  (JCVideoPlayerStandards) findViewById(R.id.jcplay);
        myjcv.setUp(SPURL
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
//        myjcv.thumbImageView.setImageResource(R.drawable._no_img);

        myjcv.setListener(this);
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
    }
    @Override
    public void showProgress() {
    }

    @Override
    public void dimissProgress() {
    }

    @Override
    public void showMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setPresenter(JCPlayContarct.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void getPlayUrl(VoidePlayBean playBean) {
        video_url = playBean.getVideo().getChapters3().get(0).getUrl();
        video_url1 = playBean.getVideo().getChapters2().get(0).getUrl();
        handler.sendEmptyMessage(HANDLERMSG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    public void fX() {
        UMVideo video = new UMVideo(video_url);
        video.setTitle(title);//视频的标题
        video.setThumb(new UMImage(JCPlayActivity.this,image));//视频的缩略图
        video.setDescription(data);//视频的描述

        new ShareAction(JCPlayActivity.this).withText(title).withMedia(video).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN).setCallback(new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {

            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {

            }
        }).open();
    }

    @Override
    public void sC() {

        instance.addKeep(data,title,image,id,duration);

    }

    @Override
    public void qxsc() {
        List<Keep> query = DBUtils.getInstance(this).collection();
        for(int i = 0; i < query.size(); i++) {
            Keep keep = query.get(i);
            instance.delete(keep);
        }
    }

    @Override
    public void quit() {
        this.finish();
    }

    @Override
    public void gaoqin() {
        SPURL = video_url;
        handler.sendEmptyMessage(HANDLERMSG);
        ToastManager.show("切换高清成功");
    }

    @Override
    public void liuchang() {
        SPURL = video_url1;
        handler.sendEmptyMessage(HANDLERMSG);
        ToastManager.show("切换标清成功");
    }

    @Override
    public String fhz() {
        return title;
    }

    class MyUserActionStandard implements JCUserActionStandard {

        @Override
        public void onEvent(int type, String url, int screen, Object... objects) {
            switch (type) {
                case JCUserAction.ON_CLICK_START_ICON:
                    Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_START_ERROR:
                    Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_START_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_PAUSE:
                    Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_RESUME:
                    Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_ENTER_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_QUIT_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_ENTER_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_QUIT_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;

                case JCUserActionStandard.ON_CLICK_START_THUMB:
                    Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserActionStandard.ON_CLICK_BLANK:
                    Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                default:
                    Log.i("USER_EVENT", "unknow");
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
