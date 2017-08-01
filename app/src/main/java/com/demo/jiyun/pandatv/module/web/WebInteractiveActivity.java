package com.demo.jiyun.pandatv.module.web;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.utils.CustomDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class WebInteractiveActivity extends BaseActivity {

    @BindView(R.id.web_iteractive_backImage)
    ImageView webIteractiveBackImage;
    @BindView(R.id.web_interactive_title)
    TextView webInteractiveTitle;
    @BindView(R.id.web_interactive_share)
    ImageView webInteractiveShare;
    @BindView(R.id.web_interactive)
    WebView webInteractive;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_interactive;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        webInteractiveTitle.setText(title);
        String url = getIntent().getStringExtra("url");
        webInteractive.loadUrl(url);
        WebSettings settings = webInteractive.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webInteractive.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webInteractive.loadUrl(url);
                return true;
            }

            //开始加载时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                CustomDialog.show(WebInteractiveActivity.this);
            }

            //加载结束时
            @Override
            public void onPageFinished(WebView view, String url) {
                CustomDialog.dimiss();
                super.onPageFinished(view, url);
            }
        });
    }

    @OnClick({R.id.web_iteractive_backImage, R.id.web_interactive_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.web_iteractive_backImage:
                finish();
                break;
            case R.id.web_interactive_share:

                break;
        }
    }

}
