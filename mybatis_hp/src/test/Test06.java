package test;

import dao.UserMapper;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class Test06 {
    public static void main(String[] args) {
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.getSession();
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=userMapper.selectById(3);
        //    User user=userMapper.selectById2(1);
            System.out.println(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.commit();
        }finally{
            MyBatisUtil.close(sqlSession);
        }
    }
}
