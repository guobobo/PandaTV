package com.demo.jiyun.pandatv.module.mine;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SetupActivity extends BaseActivity {


    @BindView(R.id.login_backImage)
    ImageView loginBackImage;
    @BindView(R.id.set_checkMsg)
    CheckBox setCheckMsg;
    @BindView(R.id.relative_msg)
    RelativeLayout relativeMsg;
    @BindView(R.id.set_checkNext)
    CheckBox setCheckNext;
    @BindView(R.id.relative_next)
    RelativeLayout relativeNext;
    @BindView(R.id.relative_delete)
    RelativeLayout relativeDelete;
    @BindView(R.id.relative_feedback)
    RelativeLayout relativeFeedback;
    @BindView(R.id.relative_upgrade)
    RelativeLayout relativeUpgrade;
    @BindView(R.id.relative_like)
    RelativeLayout relativeLike;
    @BindView(R.id.relative_about)
    RelativeLayout relativeAbout;
    @BindView(R.id.activity_set)
    LinearLayout activitySet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setup;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.login_backImage, R.id.set_checkMsg, R.id.relative_msg, R.id.set_checkNext, R.id.relative_next, R.id.relative_delete, R.id.relative_feedback, R.id.relative_upgrade, R.id.relative_like, R.id.relative_about, R.id.activity_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_backImage:
                finish();
                break;
            case R.id.set_checkMsg:
                break;
            case R.id.relative_msg:
                break;
            case R.id.set_checkNext:
                break;
            case R.id.relative_next:
                break;
            case R.id.relative_delete:
                break;
            case R.id.relative_feedback:
                break;
            case R.id.relative_upgrade:
                break;
            case R.id.relative_like:
                break;
            case R.id.relative_about:
                break;
            case R.id.activity_set:
                break;
        }
    }
}
