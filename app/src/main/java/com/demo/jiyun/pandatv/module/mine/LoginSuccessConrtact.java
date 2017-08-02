package com.demo.jiyun.pandatv.module.mine;

import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;

/**
 * Created by iu on 2017/8/2.
 */

public interface LoginSuccessConrtact {

    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {

        void showTouXiang();

    }
}
