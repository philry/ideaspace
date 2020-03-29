package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    static{
        try {
            factory=new SqlSessionFactoryBuilder().build(MyBatisUtil.class
                    .getClassLoader().getResourceAsStream("config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("mybatis初始化失败");
        }
    }

    public static SqlSession getSession(){
        SqlSession session=null;
        try {
            session=factory.openSession();
            return session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(SqlSession session){
        if(session!=null){
            session.close();
        }
    }
}
