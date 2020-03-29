package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    static{
        try {
            factory=new SqlSessionFactoryBuilder().build(MyBatisUtil.class.getClassLoader().getResourceAsStream("xml/config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Mybatis初始化失败");
        }
    }

    public static SqlSession getSqlSession(){
        SqlSession sqlSession=null;
        try {
            sqlSession=factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

    public static void close(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.close();
        }
    }
}
