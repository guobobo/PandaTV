package com.demo.jiyun.pandatv.module.mine.frament;

import android.graphics.Bitmap;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;

/**
 * Created by iu on 2017/7/31.
 */

public interface MailBoxContract {


    interface View extends BaseView<Presenter> {

        void showEmailTips(String msg);
        void hideEmailTips();
        void showPwdTips(String msg);
        void hidePwdTips();
        void showImgCode(Bitmap bitmap);
        void toLogin();

    }

    interface Presenter extends BasePresenter {
        boolean checkEmail(String emailAddress);
        boolean checkPwd(String pwd);
        boolean checkImgCode(String imgCode);
        void register(String mailAdd,String passWd,String verificationCode);
    }
}
