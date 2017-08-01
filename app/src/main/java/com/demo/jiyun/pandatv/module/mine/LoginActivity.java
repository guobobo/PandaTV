package com.demo.jiyun.pandatv.module.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.utils.ToActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_backImage)
    ImageView loginBackImage;
    @BindView(R.id.login_registeredText)
    TextView loginRegisteredText;
    @BindView(R.id.linear_weixin)
    LinearLayout linearWeixin;
    @BindView(R.id.linear_qq)
    LinearLayout linearQq;
    @BindView(R.id.linear_weibo)
    LinearLayout linearWeibo;
    @BindView(R.id.login_cntvusername)
    EditText loginCntvusername;
    @BindView(R.id.null_text)
    TextView nullText;
    @BindView(R.id.login_cntvpassward)
    EditText loginCntvpassward;
    @BindView(R.id.login_forgetText)
    TextView loginForgetText;
    @BindView(R.id.login_loginBtn)
    Button loginLoginBtn;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.login_backImage, R.id.login_registeredText, R.id.linear_weixin, R.id.linear_qq, R.id.linear_weibo, R.id.login_cntvusername, R.id.null_text, R.id.login_cntvpassward, R.id.login_forgetText, R.id.login_loginBtn, R.id.activity_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_backImage:
                finish();
                break;
            case R.id.login_registeredText:
                ToActivity.load(RegisteredActivity.class);
                break;
            case R.id.linear_weixin:
                break;
            case R.id.linear_qq:
                break;
            case R.id.linear_weibo:
                break;
            case R.id.login_cntvusername:
                break;
            case R.id.null_text:
                break;
            case R.id.login_cntvpassward:
                break;
            case R.id.login_forgetText:
                break;
            case R.id.login_loginBtn:
                break;
            case R.id.activity_login:
                break;
        }
    }
}
