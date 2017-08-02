package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public interface IPandaHomeModel extends BaseModel {

    void loadHomeList(MyNetWorkCallBack<PandaHomeBean> callBack);
    void loadPandaeyeData(String url , MyNetWorkCallBack<PandaeyeListBean> callBack);
    void loadChinaLightData(String url , MyNetWorkCallBack<HomelightListBean> callBack);
    void loadCCTVData(String url , MyNetWorkCallBack<HomecctvListBean> callBack);
    void version(MyNetWorkCallBack<UpdateBean> callBack);
}
