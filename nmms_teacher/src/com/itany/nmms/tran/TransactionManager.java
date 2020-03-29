package com.itany.nmms.tran;

public interface TransactionManager {
	
	public void beginTransaction();
	
	public void commit();
	
	public void rollback();

}
