package com.demo.jiyun.pandatv.model.biz;

import com.demo.jiyun.pandatv.model.entity.CurtureBean;
import com.demo.jiyun.pandatv.net.callback.MyNetWorkCallBack;

/**
 * Created by iu on 2017/7/27.
 */

public interface CurtureModel {
    void loadCurtureBeanList(MyNetWorkCallBack<CurtureBean> callBack);
}
