package test;

import dao.UserMapper;
import entity.User;
import entity.UserExample;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 13:45
 * Description:
 * version:1.0
 */
public class Test02 {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();

            UserMapper userMapper = session.getMapper(UserMapper.class);

            UserExample userExample = new UserExample();

            userExample.or()
                    .andUsernameEqualTo("admin")
                    .andPasswordEqualTo("admin");
            List<User> users = userMapper.selectByExample(userExample);
            System.out.println(users);


            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
