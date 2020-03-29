package com.itany.nmms.tran;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 15:20
 * Description:
 * version:1.0
 */
public interface TransactionManager {

    public void begin();

    public void commit();

    public void rollback();

}
