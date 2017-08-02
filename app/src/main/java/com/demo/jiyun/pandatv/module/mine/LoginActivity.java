package com.demo.jiyun.pandatv.module.mine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.demo.jiyun.pandatv.utils.ToastManager;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginConrtact.View {


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
    @BindView(R.id.null_pwd)
    TextView nullPwd;
    private LoginConrtact.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        new LoginPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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

                presenter.showqq();

                break;
            case R.id.linear_weibo:

                presenter.showweibo();

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
                presenter.Login(loginCntvusername.getText().toString().trim(),
                        loginCntvpassward.getText().toString().trim());
                break;
            case R.id.activity_login:
                break;
        }
    }


    @Override
    public void showphoneTips(String msg) {
        nullText.setVisibility(View.VISIBLE);
        nullText.setText(msg);
    }

    @Override
    public void hidephoneTips() {
        nullText.setVisibility(View.GONE);
    }

    @Override
    public void showPwdTips(String msg) {

        nullPwd.setVisibility(View.VISIBLE);
        nullPwd.setText(msg.trim());
    }

    @Override
    public void hidePwdTips() {

        nullPwd.setVisibility(View.GONE);
    }

    @Override
    public void showName(String name) {


    }

    @Override
    public void toLoginSuccess() {
        ToActivity.load(PersonalActivity.class);
    }

    @Override
    public void showProgress() {
        CustomDialog.show(this);
    }

    @Override
    public void dimissProgress() {
        CustomDialog.dimiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setPresenter(LoginConrtact.Presenter presenter) {
        this.presenter = presenter;
    }

}
