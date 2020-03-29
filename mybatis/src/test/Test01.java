package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/26 10:56
 * Description:
 * version:1.0
 */
public class Test01 {

    public static void main(String[] args) {
        //用于获取SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //解析配置文件，并获取到SqlSessionFactory，用于获取SqlSession
        SqlSessionFactory factory = builder.build(Test01.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));

        //获取持久化管理器，SqlSession
        //SqlSession是整个mybatis的核心
        //包含你想要的一切操作
        SqlSession session = factory.openSession();

        User user =  new User();
        user.setUsername("%");
        user.setPassword("jack");
        user.setPhone("13922345678");
        user.setAddress("江苏-南京");


        //方法一:直接使用mapper文件进行操作
        //第一个参数:你所调用的xml方法是哪一个?
        //第二个参数:该方法具体的参数是谁
//        session.insert("aa.insertUser",user);


        //方法二:通过dao接口来实现
        UserDao userDao = session.getMapper(UserDao.class);
        System.out.println(userDao);

        userDao.insertUser(user);

        session.commit();








    }


}
