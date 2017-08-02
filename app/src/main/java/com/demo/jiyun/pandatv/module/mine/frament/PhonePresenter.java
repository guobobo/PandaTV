package com.demo.jiyun.pandatv.module.mine.frament;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;

import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.model.biz.Phone;
import com.demo.jiyun.pandatv.model.biz.PhoneModel;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iu on 2017/7/31.
 */

public class PhonePresenter implements PhoneContract.Presenter {

    private PhoneContract.View phoneView;
    private PhoneModel phoneModel;
    private String jsessionid;

    public PhonePresenter(PhoneContract.View phoneView) {
        this.phoneView = phoneView;
        this.phoneView.setPresenter(this);
        this.phoneModel = new Phone();
    }

    @Override
    public boolean checkNumber(String phoneNumber) {

        if (TextUtils.isEmpty(phoneNumber)) {
            phoneView.showNumberTips("您的手机号不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            phoneView.hideNumberTips();
            return true;
        } else {
            phoneView.showNumberTips("您输入的手机格式不正确");
            return false;
        }
    }

    @Override
    public boolean checkPwd(String pwd) {

        if (pwd == null || "".equals(pwd)) {
            phoneView.showPwdTips("密码不能为空");
            return false;
        } else {
            phoneView.hidePwdTips();
            return true;
        }
    }

    @Override
    public boolean checkImgCode(String imgCode) {

        if (imgCode == null || "".equals(imgCode)) {
            phoneView.showYzmTips("图片验证码不能为空");
            return false;
        } else {
            phoneView.hideYzmTips();
            return true;
        }
    }

    @Override
    public boolean checkPhoneCode(String phoneCode) {

        if (TextUtils.isEmpty(phoneCode)) {
            phoneView.showPhoneCodeTips("手机验证码不能为空");
            return false;
        } else {
            phoneView.hidePhoneCodeTips();
            return true;
        }
    }


    @Override
    public void start() {

        phoneModel.loadImgCode(new MyNetWorkCallBack<Bundle>() {
            @Override
            public void onSuccess(Bundle bundle) {
                jsessionid = bundle.getString(Keys.JSESSIONID);
                MyLog.d("TAG","jsessionid::::"+jsessionid);
                byte[] byteArray = bundle.getByteArray(Keys.IMGCODE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                phoneView.showImgCode(bitmap);
            }
            @Override
            public void onError(String message) {
                phoneView.showMessage("网络请求失败");
            }
        });

    }

    @Override
    public void getPhoneCode(String mobileNumber, String imageCode) {

        if (!checkImgCode(imageCode))
            return;
        if (!checkNumber(mobileNumber))
            return;

        phoneModel.loadPhoneCode(mobileNumber, imageCode, jsessionid, new MyNetWorkCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                MyLog.d("TAG","获取手机验证码打印：：：："+s);
                if("success".equals(s.trim())){
                    phoneView.showMessage("获取验证码成功，请注意查收短信");
                }else {
                    phoneView.showMessage("获取验证码失败，可能您的手机已注册央视网");
                }
            }
            @Override
            public void onError(String message) {
               phoneView.showMessage("网络异常，请检查您的网络");
            }
        });

    }

    @Override
    public void register(String mobileNumber, String phoneCode, String passWd) {

        MyLog.d("TAG",mobileNumber+"......."+phoneCode+"........"+passWd);

        if(!checkNumber(mobileNumber))
            return;
        if (!checkPhoneCode(phoneCode))
            return;
        if (!checkPwd(passWd))
            return;
        phoneModel.register(mobileNumber, phoneCode, passWd, new MyNetWorkCallBack<String>() {
            @Override
            public void onSuccess(String s) {

                if ("success".equals(s.trim())){
                    phoneView.toLogin();
                }else {
                    phoneView.showMessage("注册失败，请您重新请求验证码注册");
                }

            }

            @Override
            public void onError(String message) {
                phoneView.showMessage("网络异常，请检查您的网络");
            }
        });

    }
}
