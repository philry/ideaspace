package test;

import dao.DeptDao;
import dao.EmpDao;
import entity.Depart;
import entity.Emp;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class Test07 {  //运行不了，有异常
    public static void main(String[] args) {
        SqlSession session= null;
        Depart d1=new Depart();
        d1.setName("a");
        Emp e1=new Emp();
        e1.setName("a1");
        e1.setSalary(2000.0);
        e1.setDept(d1);
        Emp e2=new Emp();
        e2.setName("a2");
        e2.setSalary(3000.0);
        e2.setDept(d1);
        Emp e3=new Emp();
        e3.setName("a3");
        e3.setSalary(4000.0);
        e3.setDept(d1);
        try {
            session=MyBatisUtil.getSession();
            DeptDao deptDao=session.getMapper(DeptDao.class);
            EmpDao empDao=session.getMapper(EmpDao.class);
            deptDao.insertDept(d1);

            empDao.insert(e1);
            empDao.insert(e2);
            empDao.insert(e3);

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally {
            MyBatisUtil.close(session);
        }

    }
}
