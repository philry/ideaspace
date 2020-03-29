package test;

import dao.UserDao;
import entity.User;
import entity.UserParameter;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        SqlSession session=null;
        try {
            session=MyBatisUtil.getSession();
            UserDao userDao=session.getMapper(UserDao.class);
           /* User user=userDao.selectById(1);
            System.out.println(user);*/

        //  List<User> users=userDao.selectAll();
        /*  UserParameter userParameter=new UserParameter();
            userParameter.setUsername("1");
            userParameter.setPassword("1");
            List<User> users=userDao.selectByNameAndPassword(userParameter);*/

        //  List<User> users=userDao.selectByNameAndPassword2("aaa","aaa");
            List<User> users=userDao.selectByNameAndPassword3("1","1");
            for(User user:users){
               System.out.println(user);
           }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally{
            MyBatisUtil.close(session);
        }
    }
}
