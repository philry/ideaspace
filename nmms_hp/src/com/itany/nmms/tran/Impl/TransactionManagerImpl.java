package com.itany.nmms.tran.Impl;

import com.itany.nmms.tran.TransactionManager;
import com.itany.nmms.util.MyBatisUtil;

public class TransactionManagerImpl implements TransactionManager {
    @Override
    public void begin() {
        MyBatisUtil.getSession();
    }

    @Override
    public void commit() {
    MyBatisUtil.getSession().commit();
    MyBatisUtil.close();
    }

    @Override
    public void rollback() {
    MyBatisUtil.getSession().rollback();
    MyBatisUtil.close();
    }
}
