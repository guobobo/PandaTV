package com.demo.jiyun.pandatv.module.mine;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;

/**
 * Created by iu on 2017/8/1.
 */

public interface LoginConrtact {

    interface View extends BaseView<Presenter> {

        void showphoneTips(String msg);
        void hidephoneTips();

        void showPwdTips(String msg);
        void hidePwdTips();


        void showName(String  name);

        void toLoginSuccess();
    }

    interface Presenter extends BasePresenter {


        boolean checkNumber(String phoneNumber);
        boolean checkPwd(String pwd);
        void Login(String number,String password);
        void getName(String name,String cookie);
        void showqq();
        void showweibo();

    }

}
