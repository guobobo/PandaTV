package com.demo.jiyun.pandatv.module.livechina;


import com.demo.jiyun.pandatv.base.BasePresenter;
import com.demo.jiyun.pandatv.base.BaseView;
import com.demo.jiyun.pandatv.model.entity.ChinaListBean;

import java.util.ArrayList;

public interface ChinaItemContarct {
    interface Presenter extends BasePresenter {
        void showLiveChinaItemData(String url);
    }
    interface View extends BaseView<Presenter> {

        void showChinaItemData(ArrayList<ChinaListBean.LiveBean> liveBeen);

    }
}
