package com.demo.jiyun.pandatv.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.jiyun.pandatv.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.context = this;
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.context = this;
    }

    protected abstract int getLayoutId();
    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
