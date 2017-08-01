package com.demo.jiyun.pandatv.module.mine;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {


    @BindView(R.id.login_backImage)
    ImageView loginBackImage;
    @BindView(R.id.collection_edit)
    TextView collectionEdit;
    @BindView(R.id.collection_tab)
    TabLayout collectionTab;
    @BindView(R.id.activity_collection)
    LinearLayout activityCollection;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void init() {

    }



    @OnClick({R.id.login_backImage, R.id.collection_edit, R.id.collection_tab, R.id.activity_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_backImage:
                finish();
                break;
            case R.id.collection_edit:
                break;
            case R.id.collection_tab:
                break;
            case R.id.activity_collection:
                break;
        }
    }
}
