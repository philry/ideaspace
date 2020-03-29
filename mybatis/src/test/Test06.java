package test;

import dao.UserMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/29 16:49
 * Description:
 * version:1.0
 */
public class Test06 {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();


            UserMapper userMapper = session.getMapper(UserMapper.class);

//            User user = userMapper.selectById(1);
            User user = userMapper.selectById2(1);
            System.out.println(user);

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            MyBatisUtil.close(session);
        }
    }

}
