package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.base.BaseModel;
import com.demo.jiyun.pandatv.model.entity.ChinaListBean;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public interface ChinaLiveModel extends BaseModel {
    void loadChinaLiveList(MyNetWorkCallBack<ChinaLiveBean> callBack);
    void loadChinaLiveItemData(String url ,MyNetWorkCallBack<ChinaListBean> callBack);
}
