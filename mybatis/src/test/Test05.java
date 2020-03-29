package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/29 14:56
 * Description:
 * version:1.0
 */
public class Test05 {

    public static void main(String[] args) {
        SqlSession session = null;
        User user = new User();
        user.setId(4);
        user.setUsername("king");
        user.setPassword("king");
        user.setPhone("13878965412");
        try {
            session = MyBatisUtil.getSession();

            UserDao userDao = session.getMapper(UserDao.class);

            userDao.updateUser(user);

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }


}
