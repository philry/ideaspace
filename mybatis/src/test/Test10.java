package test;

import dao.DeptDao;
import entity.Dept;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 11:45
 * Description:
 * version:1.0
 */
public class Test10 {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            DeptDao deptDao = session.getMapper(DeptDao.class);

            Dept dept = deptDao.selectById1(1);
            System.out.println(dept);

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
