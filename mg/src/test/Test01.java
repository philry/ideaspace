package test;

import dao.UserMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 13:43
 * Description:
 * version:1.0
 */
public class Test01 {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("mg");
        user.setPassword("mg");
        user.setPhone("12345678912");
        user.setAddress("江苏-南京");
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            UserMapper userMapper = session.getMapper(UserMapper.class);

            userMapper.insertSelective(user);

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
