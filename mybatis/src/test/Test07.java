package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 9:08
 * Description:
 * version:1.0
 */
public class Test07 {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("root");
        user.setPhone("13812456985");
        user.setPassword("123456");
        user.setAddress("江苏-苏州");

        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            UserDao userDao = session.getMapper(UserDao.class);

            System.out.println("保存前主键的值"+user.getId());

            userDao.insertReturnPrimaryKey(user);

            System.out.println("保存后主键的值"+user.getId());

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
