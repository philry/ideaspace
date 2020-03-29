package dao;

import entity.Emp;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 9:58
 * Description:
 * version:1.0
 */
public interface EmpDao {

    public void insertEmp(Emp emp);


    public Emp selectById(Integer id);
    public Emp selectById2(Integer id);
    public Emp selectById3(Integer id);


    public List<Emp> selectAll();

}
