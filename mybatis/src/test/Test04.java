package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/29 13:38
 * Description:
 * version:1.0
 */
public class Test04 {

    public static void main(String[] args) {
        User userParam = new User();
//        userParam.setId(1);
        userParam.setUsername("admin");
        userParam.setPassword("admin");
        userParam.setPhone("13812345678");
        userParam.setAddress("江苏-南京");
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();


            UserDao userDao = session.getMapper(UserDao.class);

//            List<User> users = userDao.selectByParams1(userParam);
//            List<User> users = userDao.selectByParams2(userParam);
//            List<User> users = userDao.selectByParams3(userParam);
//            List<User> users = userDao.selectByParams4(userParam);

            List<Integer> ids = new ArrayList<Integer>();
            ids.add(new Integer(1));
            ids.add(new Integer(3));
            ids.add(new Integer(4));
            List<User> users = userDao.selectByIds(ids);


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
