package com.demo.jiyun.pandatv.activity;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.module.home.HomeFragment;
import com.demo.jiyun.pandatv.module.home.HomePresenter;
import com.demo.jiyun.pandatv.module.interactive.InteractiveActivity;
import com.demo.jiyun.pandatv.module.livechina.LivechinaFragment;
import com.demo.jiyun.pandatv.module.mine.PersonalActivity;
import com.demo.jiyun.pandatv.module.pandaculture.PandacultureFragment;
import com.demo.jiyun.pandatv.module.pandaeye.PandaeyeFragment;
import com.demo.jiyun.pandatv.module.pandalive.PandaliveFragment;
import com.demo.jiyun.pandatv.utils.FragmentBuild;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.demo.jiyun.pandatv.utils.ToastManager;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iconImg)
    ImageView iconImg;
    @BindView(R.id.personImg)
    ImageView personImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.hudongImg)
    ImageView hudongImg;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.homePage)
    RadioButton homePage;
    @BindView(R.id.homePandaLive)
    RadioButton homePandaLive;
    @BindView(R.id.homePandaculture)
    RadioButton homePandaculture;
    @BindView(R.id.homePandaBroadcast)
    RadioButton homePandaBroadcast;
    @BindView(R.id.homeLiveChina)
    RadioButton homeLiveChina;
    @BindView(R.id.homeBottomGroup)
    RadioGroup homeBottomGroup;

    private long lastTime;//上一次点击back键的时间毫秒数
    public static final int HOMETYPE = 1;
    private FragmentManager fragmentManager;
//    private static Fragment lastFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {


        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = (HomeFragment) FragmentBuild.changeFragment(HomeFragment.class, R.id.container, true, null, true);

        new HomePresenter(homeFragment);
    }

    @OnClick({R.id.iconImg, R.id.personImg, R.id.titleTv, R.id.hudongImg, R.id.container, R.id.homePage, R.id.homePandaLive, R.id.homePandaculture, R.id.homePandaBroadcast, R.id.homeLiveChina, R.id.homeBottomGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iconImg:
                break;
            case R.id.personImg:
                ToActivity.load(PersonalActivity.class);
                break;
            case R.id.titleTv:
                break;
            case R.id.hudongImg:
                ToActivity.load(InteractiveActivity.class);
                break;
            case R.id.container:
                break;
            case R.id.homePage:

                showTitle(null,HOMETYPE);
                FragmentBuild.changeFragment(HomeFragment.class, R.id.container, true, null, true);

                break;
            case R.id.homePandaLive:
                showTitle("熊猫直播",0);
                FragmentBuild.changeFragment(PandaliveFragment.class,R.id.container,true,null,true);
                break;
            case R.id.homePandaculture:

                showTitle("熊猫文化",0);
                FragmentBuild.changeFragment(PandacultureFragment.class,R.id.container,true,null,true);
                break;
            case R.id.homePandaBroadcast:
                showTitle("熊猫播报",0);
                FragmentBuild.changeFragment(PandaeyeFragment.class,R.id.container,true,null,true);
                break;
            case R.id.homeLiveChina:
                showTitle("直播中国",0);
                FragmentBuild.changeFragment(LivechinaFragment.class,R.id.container,true,null,true);
                break;
            case R.id.homeBottomGroup:
                break;
        }
    }

    /**
     * 显示标题的方法
     * @param title 显示的标题
     * @param type 1代表首页
     */
    private void showTitle(String title,int type){
        if(type == HOMETYPE){
            iconImg.setVisibility(View.VISIBLE);
            titleTv.setVisibility(View.GONE);
            hudongImg.setVisibility(View.VISIBLE);
        }else {
            titleTv.setText(title);
            iconImg.setVisibility(View.GONE);
            titleTv.setVisibility(View.VISIBLE);
            hudongImg.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        String topFragmentName = getStackTopFragmentName();
        if("HomeFragment".equals(topFragmentName)||"LivechinaFragment".equals(topFragmentName)||
                "PandacultureFragment".equals(topFragmentName)||"PandaeyeFragment".equals(topFragmentName)||
                "PandaliveFragment".equals(topFragmentName)){
            if(System.currentTimeMillis() - lastTime < 2000){
                finish();
            }else {
                ToastManager.show("再按一次退出应用");
                lastTime = System.currentTimeMillis();
            }
        }else {
            fragmentManager.popBackStackImmediate();
            FragmentBuild.lastFragment  = (BaseFragment) fragmentManager.findFragmentByTag(getStackTopFragmentName());
        }
    }
    public String getStackTopFragmentName(){
        int entryCount = fragmentManager.getBackStackEntryCount();
        FragmentManager.BackStackEntry stackEntryAt = fragmentManager.getBackStackEntryAt(entryCount - 1);
        return stackEntryAt.getName();
    }

}


