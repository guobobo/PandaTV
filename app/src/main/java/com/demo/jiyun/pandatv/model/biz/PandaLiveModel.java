package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.model.entity.LookchatBean;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.model.entity.PandaMultipleBean;
import com.demo.jiyun.pandatv.model.entity.WonderfulBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public interface PandaLiveModel extends BaseModel {
    void loadPandaLiveList(MyNetWorkCallBack<PandaLiveBean> callBack);
    void getWonderfulData(String vsid,String n,String serviceId,String o,String of,String p,  MyNetWorkCallBack<WonderfulBean> callBack);
    void getMultipleData(MyNetWorkCallBack<PandaMultipleBean> callBack);
    void getLookchatData(MyNetWorkCallBack<LookchatBean> callBack);
}
