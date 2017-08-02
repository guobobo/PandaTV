package com.demo.jiyun.pandatv.module.mine;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.utils.ToActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.demo.jiyun.pandatv.R.id.liner_login_success;
import static com.demo.jiyun.pandatv.R.id.personal_avatarusername_success;

public class PersonalActivity extends BaseActivity  {


    @BindView(R.id.personal_backImage)
    ImageView personalBackImage;
    @BindView(R.id.personal_avatarImage)
    ImageView personalAvatarImage;
    @BindView(R.id.liner_login)
    LinearLayout linerLogin;
    @BindView(R.id.personal_avatarImage_success)
    ImageView personalAvatarImageSuccess;
    @BindView(personal_avatarusername_success)
    TextView personalAvatarusernameSuccess;
    @BindView(liner_login_success)
    LinearLayout linerLoginSuccess;
    @BindView(R.id.liner_history)
    LinearLayout linerHistory;
    @BindView(R.id.liner_collection)
    LinearLayout linerCollection;
    @BindView(R.id.linear_set)
    LinearLayout linearSet;
    @BindView(R.id.activity_personal)
    LinearLayout activityPersonal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void init() {
        SharedPreferences sharedPreferences = getSharedPreferences(Keys.LOGINSTATE, MODE_PRIVATE);
        final String string = sharedPreferences.getString(Keys.USERNAME, "");
        if(!string.equals("")) {

            linerLogin.setVisibility(View.GONE);
            linerLoginSuccess.setVisibility(View.VISIBLE);
            personalAvatarusernameSuccess.setText(string);
        }

    }


    @OnClick({R.id.liner_login, liner_login_success, R.id.liner_history, R.id.liner_collection, R.id.linear_set,R.id.personal_backImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liner_login:
                ToActivity.load(LoginActivity.class);
                break;
            case liner_login_success:
                ToActivity.load(LoginSuccessActivity.class);
                break;
            case R.id.liner_history:
                ToActivity.load(HistoryActivity.class);
                break;
            case R.id.liner_collection:
                ToActivity.load(CollectionActivity.class);
                break;
            case R.id.linear_set:
                ToActivity.load(SetupActivity.class);
                break;
            case R.id.personal_backImage:
                finish();
                break;
        }
    }

}