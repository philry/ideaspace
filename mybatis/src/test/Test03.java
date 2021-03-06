package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/29 10:29
 * Description:
 * version:1.0
 */
public class Test03 {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            UserDao userDao = session.getMapper(UserDao.class);
//            User user = userDao.selectById2(1);
//            User user = userDao.selectById3(1);
//            System.out.println(user);


//            List<User> users = userDao.selectByUsername("%/%%");
            List<User> users = userDao.selectByUsername2("/%");
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
