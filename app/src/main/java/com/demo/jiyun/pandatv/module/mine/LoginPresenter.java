package com.demo.jiyun.pandatv.module.mine;

import android.os.Bundle;
import android.text.TextUtils;

import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.model.biz.Login;
import com.demo.jiyun.pandatv.model.biz.LoginModel;
import com.demo.jiyun.pandatv.model.entity.LoginBean;
import com.demo.jiyun.pandatv.model.entity.NiChengBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;
import com.demo.jiyun.pandatv.utils.SharedPreferencesManager;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iu on 2017/8/1.
 */

public class LoginPresenter implements LoginConrtact.Presenter {

    private LoginModel loginModel;
    private LoginConrtact.View loginView;
    private String mUserSeqId;

    public LoginPresenter(LoginConrtact.View loginView){
        this.loginView = loginView;
        loginModel = new Login();
        this.loginView.setPresenter(this);
    }

    @Override
    public boolean checkNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            loginView.showphoneTips("您的手机号/邮箱不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            loginView.hidephoneTips();
            return true;
        } else {
            loginView.showphoneTips("您输入的手机格式不正确");
            return false;
        }
    }

    @Override
    public boolean checkPwd(String pwd) {
        if (pwd == null || "".equals(pwd)) {
            loginView.showPwdTips("密码不能为空");
            return false;
        } else {
            loginView.hidePwdTips();
            return true;
        }
    }

    @Override
    public void Login(String number, String password) {

        if(!checkNumber(number))
            return;
        if(!checkPwd(password))
            return;

        loginModel.toLogin(number, password, new MyNetWorkCallBack<Bundle>() {
            @Override
            public void onSuccess(Bundle bundle) {

//                getName(mUserSeqId,);

                String cookie = bundle.getString("cookie");

                LoginBean loginBean = bundle.getParcelable("loginBean");

                String errMsg = loginBean.getErrMsg();
                mUserSeqId = loginBean.getUser_seq_id();
                getName(mUserSeqId,cookie);

                MyLog.d("TAG","-------------"+ mUserSeqId);
                MyLog.d("TAG","-------------"+errMsg+"--------"+cookie);

                if("成功".equals(errMsg.trim())){
                    SharedPreferencesManager.saveUserInfo(Keys.USERSTATE,errMsg);
                    loginView.toLoginSuccess();
                }else {
                    loginView.showMessage(errMsg.trim());
                }
            }

            @Override
            public void onError(String message) {
                loginView.showMessage("您的网络异常");
            }
        });
    }

    @Override
    public void getName(String name ,String cookie) {

        loginModel.getName(name,cookie,new MyNetWorkCallBack<NiChengBean>() {
            @Override
            public void onSuccess(NiChengBean niChengBean) {
                String nickname = niChengBean.getContent().getNickname();
                MyLog.d("TAG",nickname+"-------------");
                SharedPreferencesManager.saveUserInfo(Keys.USERNAME,nickname);

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void showqq() {

        UMShareAPI.get(App.context).doOauthVerify(App.context, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                MyLog.d("TAG", "onStart");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                MyLog.d("TAG", "onComplete");


                Set<String> set = map.keySet();
                for (String string : set) {
                    Log.e("TAG",
                            "============================Map=========================");
                    Log.e("TAG", string + "::::" + map.get(string));
                    String str = map.get(string);
                }

                ToActivity.load(LoginSuccessActivity.class);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                MyLog.d("TAG", "onError");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                MyLog.d("TAG", "onCancel");
            }
        });

    }

    @Override
    public void showweibo() {

        UMShareAPI.get(App.context).doOauthVerify(App.context, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

                MyLog.d("TAG", "--------onStart");
                Log.e("TAG", "--------onStart");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                MyLog.d("TAG", "--------onComplete");

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                MyLog.d("TAG", "---------onError");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

                MyLog.d("TAG", "---------onCancel");
            }
        });

    }

    @Override
    public void start() {
    }
}
