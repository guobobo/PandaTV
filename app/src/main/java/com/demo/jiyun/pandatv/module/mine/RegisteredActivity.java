package com.demo.jiyun.pandatv.module.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.module.mine.adapter.CollectionFragmentAdapetr;
import com.demo.jiyun.pandatv.module.mine.frament.MailBoxFragment;
import com.demo.jiyun.pandatv.module.mine.frament.PhoneFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisteredActivity extends BaseActivity {

    @BindView(R.id.login_backImage)
    ImageView loginBackImage;
    @BindView(R.id.registered_tab)
    TabLayout registeredTab;
    @BindView(R.id.registered_viewpager)
    ViewPager registeredViewpager;
    @BindView(R.id.activity_registered)
    LinearLayout activityRegistered;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    protected void init() {


        PhoneFragment phoneFragment = new PhoneFragment();
        MailBoxFragment mailBoxFragment = new MailBoxFragment();

        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(phoneFragment);
        list.add(mailBoxFragment);
        String[] strings = {"手机注册","邮箱注册"};

        CollectionFragmentAdapetr collectionFragmentAdapetr = new CollectionFragmentAdapetr(getSupportFragmentManager(), list, strings);

        registeredViewpager.setAdapter(collectionFragmentAdapetr);

        registeredTab.setupWithViewPager(registeredViewpager);
        registeredTab.setTabMode(TabLayout.MODE_FIXED);

    }



    @OnClick(R.id.login_backImage)
    public void onViewClicked() {
        finish();
    }
}
