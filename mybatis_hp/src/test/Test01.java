package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(Test01.class.getClassLoader().getResourceAsStream("config.xml"));
        SqlSession session=factory.openSession();

        User user =  new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPhone("10123456789");
        user.setAddress("南京");

        UserDao userDao=session.getMapper(UserDao.class);
        System.out.println(userDao);
        userDao.insert(user);
        session.commit();
    }
}
