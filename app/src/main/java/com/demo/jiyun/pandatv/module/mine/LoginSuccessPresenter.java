package com.demo.jiyun.pandatv.module.mine;

/**
 * Created by iu on 2017/8/2.
 */

public class LoginSuccessPresenter implements LoginSuccessConrtact.Presenter {


    private LoginSuccessConrtact.View loginView;

    public LoginSuccessPresenter(LoginSuccessConrtact.View loginView) {
        this.loginView = loginView;
        this.loginView.setPresenter(this);
    }

    @Override
    public void showTouXiang() {

    }

    @Override
    public void start() {

    }
}
