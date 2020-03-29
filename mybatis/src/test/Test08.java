package test;

import dao.DeptDao;
import dao.EmpDao;
import entity.Dept;
import entity.Emp;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 10:02
 * Description:
 * version:1.0
 */
public class Test08 {

    public static void main(String[] args) {

        Dept dept = new Dept();
        dept.setName("d1");

        Emp e1 = new Emp();
        e1.setName("e1");
        e1.setSalary(8000.0);
        e1.setDept(dept);

        Emp e2 = new Emp();
        e2.setName("e2");
        e2.setSalary(6000.0);
        e2.setDept(dept);

        Emp e3 = new Emp();
        e3.setName("e3");
        e3.setSalary(9000.0);
        e3.setDept(dept);



        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            DeptDao deptDao = session.getMapper(DeptDao.class);
            EmpDao empDao = session.getMapper(EmpDao.class);


            deptDao.insertDept(dept);
            empDao.insertEmp(e1);
            empDao.insertEmp(e2);
            empDao.insertEmp(e3);


            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
