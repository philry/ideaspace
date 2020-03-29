package test;

import dao.EmpDao;
import entity.Emp;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 10:33
 * Description:
 * version:1.0
 */
public class Test09 {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            EmpDao empDao = session.getMapper(EmpDao.class);

//            Emp emp = empDao.selectById(2);
//            Emp emp = empDao.selectById2(2);
//            Emp emp = empDao.selectById3(2);

//            System.out.println(emp);

            List<Emp> emps = empDao.selectAll();
            for(Emp emp : emps){
                System.out.println(emp);
            }

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
