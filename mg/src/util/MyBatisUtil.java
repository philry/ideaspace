package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/26 14:17
 * Description:
 * version:1.0
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory;

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(
                    MyBatisUtil.class
                            .getClassLoader()
                            .getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e){
            e.printStackTrace();
            throw new ExceptionInInitializerError("mybatis初始化失败");
        }
    }

    public static SqlSession getSession(){
        SqlSession session = null;
        try {
            session = factory.openSession();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return session;
    }


    public static void close(SqlSession session){
        if(session != null){
            session.close();
        }
    }

}
