package com.itany.nmms.tran.impl;

import com.itany.nmms.tran.TransactionManager;
import com.itany.nmms.util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 15:20
 * Description:
 * version:1.0
 */
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
