package dao;

import entity.Dept;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 9:58
 * Description:
 * version:1.0
 */
public interface DeptDao {

    public void insertDept(Dept dept);

    public Dept selectById1(Integer id);

}
