package com.itany.nmms.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(MyBatisUtil.class.getClassLoader()
                    .getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("MyBatis初始化失败");
        }
    }

    public static SqlSession getSession() {
        SqlSession session = local.get();
        if (session == null) {  //若local中没有session,就
            session = factory.openSession();//从工厂中创建一个session给local
            local.set(session);
        }
        return session;
    }

    public static void close() {
        SqlSession session = getSession();
        session.close();
        local.remove();
    }
}
