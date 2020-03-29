package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class Test05 {
    public static void main(String[] args) {
    SqlSession session=null;
    User user=new User();
    user.setId(4);
    user.setUsername("bbb");
    user.setPassword("222");
    user.setPhone("12345678900");
    user.setAddress("上海");
        try {
            session=MyBatisUtil.getSession();
            UserDao userDao=session.getMapper(UserDao.class);
            userDao.update(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally {
            MyBatisUtil.close(session);
        }
    }
}
