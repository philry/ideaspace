package com.itany.nmms.tran.impl;

import com.itany.nmms.tran.TransactionManager;
import com.itany.nmms.util.MyBatisUtil;

public class TransactionManagerImpl implements TransactionManager{

	public void beginTransaction() {
		MyBatisUtil.getSession();
	}

	public void commit() {
		MyBatisUtil.getSession().commit();
		MyBatisUtil.close();
	}

	public void rollback() {
		MyBatisUtil.getSession().rollback();
		MyBatisUtil.close();
	}

}
