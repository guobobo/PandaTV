package com.demo.jiyun.pandatv.model.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDBManager {
    private static final String DBNAME="panda_db";
    private static MyDBManager manager;
    private Context context;
    private DaoMaster.DevOpenHelper helper;

    public MyDBManager(Context context){
        this.context=context;
        helper=new DaoMaster.DevOpenHelper(context,DBNAME,null);
    }

    public static MyDBManager getInstance(Context context){
        if(manager==null){
            synchronized (MyDBManager.class){
                if(manager==null ) {
                    manager=new MyDBManager(context);
                }
            }
        }
        return manager;
    }

    public SQLiteDatabase getRead(){
        return helper.getReadableDatabase();
    }

    public SQLiteDatabase getWrit(){
        return helper.getWritableDatabase();
    }

}
