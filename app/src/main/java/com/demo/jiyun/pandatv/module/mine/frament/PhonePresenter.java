package com.demo.jiyun.pandatv.module.mine.frament;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.demo.jiyun.pandatv.config.Keys;
import com.demo.jiyun.pandatv.model.biz.Phone;
import com.demo.jiyun.pandatv.model.biz.PhoneModel;
import com.demo.jiyun.pandatv.model.entity.RegisterBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;
import com.demo.jiyun.pandatv.utils.MyLog;

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
    public boolean checkNumber(String emailAddress) {
        return false;
    }

    @Override
    public boolean checkPwd(String pwd) {
        return false;
    }

    @Override
    public boolean checkImgCode(String imgCode) {
        return false;
    }

    @Override
    public boolean checkPhoneCode() {
        return false;
    }

    @Override
    public void getPhoneCode(String mobileNumber, String imageCode) {

        phoneModel.loadPhoneCode(mobileNumber, imageCode, jsessionid, new MyNetWorkCallBack<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean registerBean) {

                String msg = registerBean.getMsg();

                MyLog.d("TAG","手机验证码状态“：：：：："+msg);

            }

            @Override
            public void onError(String message) {
                MyLog.d("TAG","请求失败：：：：："+message);
            }
        });

    }

    @Override
    public void register(String mailAdd, String passWd, String verificationCode) {

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
}
