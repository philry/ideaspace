package com.itany.netClass.transaction;
/**
 * 事务接口
 * @author teacher
 * @date 2018-8-22
 */
public interface TransactionManager {
	public void beginTransaction();
	public void commit();
	public void rollback();
}
