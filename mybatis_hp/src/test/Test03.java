package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

public class Test03 {
    public static void main(String[] args) {
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.getSession();
            UserDao userDao=sqlSession.getMapper(UserDao.class);
            User user=userDao.selectById2(1);
            System.out.println(user);
           /* List<User> users=userDao.selectByName("%/a%");
            for(User user:users){
                System.out.println(user);
            }*/
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.commit();
        }finally {
            MyBatisUtil.close(sqlSession);
        }
    }
}
