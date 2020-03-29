package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

public class Test04 {
    public static void main(String[] args) {
        User userParam=new User();
     //   userParam.setId(1);
        userParam.setUsername("aaa");
        userParam.setPassword("aaa");
        userParam.setPhone("10123456789 ");
        userParam.setAddress("南京");
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.getSession();
            UserDao userDao=sqlSession.getMapper(UserDao.class);
        //    List<User> users=userDao.selectByParam(userParam);
            List<User> users=userDao.selectByParam4(userParam);
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
