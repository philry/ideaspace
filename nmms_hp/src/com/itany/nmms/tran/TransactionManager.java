package com.itany.nmms.tran;

public interface TransactionManager {
    public void begin();
    public void commit();
    public void rollback();
}
