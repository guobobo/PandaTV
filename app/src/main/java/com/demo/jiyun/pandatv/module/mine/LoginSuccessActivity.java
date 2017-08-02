package com.demo.jiyun.pandatv.module.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.utils.CustomAuthority;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginSuccessActivity extends BaseActivity implements LoginSuccessConrtact.View{

    @BindView(R.id.login_success_backImage)
    ImageView loginSuccessBackImage;
    @BindView(R.id.avatarImage_loginsuccess)
    ImageView avatarImageLoginsuccess;
    @BindView(R.id.linear_loginsuccess_tuoxiang)
    LinearLayout linearLoginsuccessTuoxiang;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.mNickname_loginsuccess)
    TextView mNicknameLoginsuccess;
    @BindView(R.id.relative_loginsuccess_nicheng)
    RelativeLayout relativeLoginsuccessNicheng;
    @BindView(R.id.Btn_loginsuccess)
    Button BtnLoginsuccess;
    @BindView(R.id.activity_login_success)
    LinearLayout activityLoginSuccess;
    private String mName;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_success;
    }

    @Override
    protected void init() {

        sharedPreferences = getSharedPreferences(Keys.LOGINSTATE, MODE_PRIVATE);
        edit = sharedPreferences.edit();
        mName = sharedPreferences.getString(Keys.USERNAME, "");
        mNicknameLoginsuccess.setText(mName);

    }


    @OnClick({R.id.login_success_backImage, R.id.avatarImage_loginsuccess, R.id.imageView, R.id.Btn_loginsuccess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_success_backImage:
                finish();
                break;
            case R.id.avatarImage_loginsuccess:


                CustomAuthority.Addto();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型
                startActivityForResult(intent, 1000);



                break;
            case R.id.imageView:
                break;
            case R.id.Btn_loginsuccess:
                edit.clear();
                edit.commit();
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(LoginSuccessConrtact.Presenter presenter) {

    }
}
