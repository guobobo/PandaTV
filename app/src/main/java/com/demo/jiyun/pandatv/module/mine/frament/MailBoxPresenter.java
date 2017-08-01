package com.demo.jiyun.pandatv.module.mine.frament;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.model.biz.MailBox;
import com.demo.jiyun.pandatv.model.biz.MailBoxModel;
import com.demo.jiyun.pandatv.model.entity.RegisterBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iu on 2017/7/31.
 */

public class MailBoxPresenter implements MailBoxContract.Presenter {

    private MailBoxContract.View mvmailboxview;
    private MailBoxModel mailBoxModel;
    private String jsessionid;

    public MailBoxPresenter(MailBoxContract.View mvmailboxview) {
        this.mvmailboxview = mvmailboxview;
        this.mvmailboxview.setPresenter(this);
        this.mailBoxModel = new MailBox();
    }

    @Override
    public void start() {

        mailBoxModel.loadImgCode(new MyNetWorkCallBack<Bundle>() {
            @Override
            public void onSuccess(Bundle bundle) {
                jsessionid = bundle.getString(Keys.JSESSIONID);
                MyLog.d("TAG","jsessionid::::"+jsessionid);
                byte[] byteArray = bundle.getByteArray(Keys.IMGCODE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mvmailboxview.showImgCode(bitmap);
            }
            @Override
            public void onError(String message) {
            }
        });

    }


    //检查邮箱
    @Override
    public boolean checkEmail(String emailAddress) {
        if(emailAddress == null || "".equals(emailAddress)){
            mvmailboxview.showEmailTips("邮箱不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(emailAddress);

        boolean matches = matcher.matches();
        if(!matches){
            mvmailboxview.showEmailTips("邮箱格式不正确");
            return false;
        }
        mvmailboxview.hideEmailTips();
        return  true;
    }

    //检查密码
    @Override
    public boolean checkPwd(String pwd) {
        if(pwd == null || "".equals(pwd)){
            mvmailboxview.showEmailTips("密码不能为空");
            return false;
        }
        return true;
    }

    //检查验证码
    @Override
    public boolean checkImgCode(String imgCode) {
        if(imgCode == null || "".equals(imgCode)){
            mvmailboxview.showEmailTips("验证码不能为空");
            return false;
        }
        return true;
    }

    //注册
    @Override
    public void register(String mailAdd, String passWd, String verificationCode) {

        if(!checkEmail(mailAdd))
            return;
        if(!checkPwd(passWd))
            return;
        if(!checkImgCode(verificationCode))
            return;

        mailBoxModel.register(mailAdd, passWd, verificationCode, jsessionid, new MyNetWorkCallBack<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean register) {

                MyLog.d("TAG",register.getMsg());

                String msg = register.getMsg();
                if("成功".equals(msg)){
                    mvmailboxview.toLogin();

                }else {
                    mvmailboxview.showMessage(msg);
                }
            }

            @Override
            public void onError( String errorMsg) {
                mvmailboxview.showMessage(errorMsg);
            }
        });

    }


}
