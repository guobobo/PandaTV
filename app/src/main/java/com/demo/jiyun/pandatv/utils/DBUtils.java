package com.demo.jiyun.pandatv.utils;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.demo.jiyun.pandatv.model.db.DaoMaster;
import com.demo.jiyun.pandatv.model.db.DaoSession;
import com.demo.jiyun.pandatv.model.db.History;
import com.demo.jiyun.pandatv.model.db.HistoryDao;
import com.demo.jiyun.pandatv.model.db.Keep;
import com.demo.jiyun.pandatv.model.db.KeepDao;
import com.demo.jiyun.pandatv.model.db.MyDBManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DBUtils {
    private Context context;
    private static DBUtils collectionUtils;

    public DBUtils(Context context){
        this.context=context;
    }

    public static DBUtils getInstance(Context context){
        if(collectionUtils==null){
            synchronized (DBUtils.class){
                if(collectionUtils==null){
                    collectionUtils=new DBUtils(context);
                }
            }
        }
        return collectionUtils;
    }

    public void addKeep(String data,String title,String image,String id,String duration){
        Keep keep = new Keep();
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        data = sdf.format(date);
        keep.setData(data);
        keep.setTitle(title);
        keep.setImage(image);
        keep.setUrl(id);
        keep.setDuration(duration);
        keep.setFlag(false);
        keep.setVisibility(false);
        insert(keep);
    }

    public void addHistory(String data,String title,String image,String id,String duration){
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        data = sdf.format(date);
        History history=new History();
        history.setData(data);
        history.setTitle(title);
        history.setImage(image);
        history.setUrl(id);
        history.setDuration(duration);
        history.setFlag(false);
        history.setVisibility(false);
        insert(history);
    }

    public List<Keep> collection(){
        SQLiteDatabase read = MyDBManager.getInstance(context).getRead();
        DaoMaster master=new DaoMaster(read);
        DaoSession daoSession = master.newSession();
        KeepDao keepDao = daoSession.getKeepDao();
        QueryBuilder<Keep> builder = keepDao.queryBuilder();
        List<Keep> list = builder.list();
        return list;
    }

    public List<History> history(){
        SQLiteDatabase read = MyDBManager.getInstance(context).getRead();
        DaoMaster master=new DaoMaster(read);
        DaoSession daoSession = master.newSession();
        HistoryDao historyDao = daoSession.getHistoryDao();
        QueryBuilder<History> builder = historyDao.queryBuilder();
        List<History> list = builder.list();
        return list;
    }

    public void insert(Keep keep){
        SQLiteDatabase writ = MyDBManager.getInstance(context).getWrit();
        DaoMaster master=new DaoMaster(writ);
        DaoSession daoSession = master.newSession();
        KeepDao keepDao = daoSession.getKeepDao();
        keepDao.insert(keep);
    }

    public void insert(History history){
        SQLiteDatabase writ = MyDBManager.getInstance(context).getWrit();
        DaoMaster master=new DaoMaster(writ);
        DaoSession daoSession = master.newSession();
        HistoryDao historyDao = daoSession.getHistoryDao();
        historyDao.insert(history);
    }

    public void delete(Keep keep) {
        SQLiteDatabase write = MyDBManager.getInstance(context).getWrit();
        DaoMaster master = new DaoMaster(write);
        DaoSession daoSession = master.newSession();
        KeepDao keepDao = daoSession.getKeepDao();
        keepDao.delete(keep);
    }

    public void delete(History history){
        SQLiteDatabase writ = MyDBManager.getInstance(context).getWrit();
        DaoMaster master=new DaoMaster(writ);
        DaoSession daoSession = master.newSession();
        HistoryDao historyDao = daoSession.getHistoryDao();
        historyDao.delete(history);
    }

    public void update(Keep keep){
        SQLiteDatabase write = MyDBManager.getInstance(context).getWrit();
        DaoMaster master = new DaoMaster(write);
        DaoSession daoSession = master.newSession();
        KeepDao keepDao = daoSession.getKeepDao();
        keepDao.update(keep);
    }

    public void update(History history){
        SQLiteDatabase writ = MyDBManager.getInstance(context).getWrit();
        DaoMaster master=new DaoMaster(writ);
        DaoSession daoSession = master.newSession();
        HistoryDao historyDao = daoSession.getHistoryDao();
        historyDao.update(history);
    }

}
