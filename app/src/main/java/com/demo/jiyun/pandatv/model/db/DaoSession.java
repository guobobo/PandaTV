package com.demo.jiyun.pandatv.model.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.demo.jiyun.pandatv.model.db.History;
import com.demo.jiyun.pandatv.model.db.Keep;

import com.demo.jiyun.pandatv.model.db.HistoryDao;
import com.demo.jiyun.pandatv.model.db.KeepDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyDaoConfig;
    private final DaoConfig keepDaoConfig;

    private final HistoryDao historyDao;
    private final KeepDao keepDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyDaoConfig = daoConfigMap.get(HistoryDao.class).clone();
        historyDaoConfig.initIdentityScope(type);

        keepDaoConfig = daoConfigMap.get(KeepDao.class).clone();
        keepDaoConfig.initIdentityScope(type);

        historyDao = new HistoryDao(historyDaoConfig, this);
        keepDao = new KeepDao(keepDaoConfig, this);

        registerDao(History.class, historyDao);
        registerDao(Keep.class, keepDao);
    }
    
    public void clear() {
        historyDaoConfig.clearIdentityScope();
        keepDaoConfig.clearIdentityScope();
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }

    public KeepDao getKeepDao() {
        return keepDao;
    }

}
