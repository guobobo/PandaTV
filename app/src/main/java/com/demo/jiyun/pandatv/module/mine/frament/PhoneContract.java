package com.demo.jiyun.pandatv.module.mine.frament;

import android.graphics.Bitmap;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;

/**
 * Created by iu on 2017/7/31.
 */

public interface PhoneContract {


    interface View extends BaseView<PhoneContract.Presenter> {

        void showNumberTips(String msg);
        void hideNumberTips();

        void showPwdTips(String msg);
        void hidePwdTips();

        void showImgCode(Bitmap bitmap);
        void showPhoneCode(String code);

        void showYzmTips(String msg);
        void hideYzmTips();

        void toLogin();

    }

    interface Presenter extends BasePresenter {
        boolean checkNumber(String emailAddress);
        boolean checkPwd(String pwd);
        boolean checkImgCode(String imgCode);
        boolean checkPhoneCode();
        void getPhoneCode(String mobileNumber, String imageCode);
        void register(String mailAdd,String passWd,String verificationCode);
    }

}
