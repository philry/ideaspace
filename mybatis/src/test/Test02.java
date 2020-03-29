package test;

import dao.UserDao;
import entity.User;
import entity.UserParameter;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/26 14:20
 * Description:
 * version:1.0
 */
public class  Test02 {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            UserDao userDao = session.getMapper(UserDao.class);

//            User user = userDao.selectById(1);

//            System.out.println(user);

//            List<User> users = userDao.selectAll();

            UserParameter userParameter = new UserParameter();
            userParameter.setUsername("admin");
            userParameter.setPassword("admin");

//            List<User> users = userDao.selectByUsernameAndPassword(userParameter);
//            List<User> users = userDao.selectByUsernameAndPassword2("admin","admin");
            List<User> users = userDao.selectByUsernameAndPassword3("alice","alice");

            for(User user : users){
                System.out.println(user);
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
