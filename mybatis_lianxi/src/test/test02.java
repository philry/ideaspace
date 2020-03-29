package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

public class test02 {
    public static void main(String[] args) {
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.getSqlSession();
            UserDao userDao=sqlSession.getMapper(UserDao.class);
        /*    User user=userDao.selectById(2);
            System.out.println(user);*/

            List<User> users=userDao.selectAll();
            for(User user:users){
                System.out.println(user);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            MyBatisUtil.close(sqlSession);
        }
    }

}
