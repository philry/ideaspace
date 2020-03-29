package com.itany.nmms.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();
	
	
	static{
		try {
			factory = new SqlSessionFactoryBuilder().build(MyBatisUtil
						.class
						.getClassLoader()
						.getResourceAsStream("mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("mybatis初始化失败");
		}
	}

	
	public static SqlSession getSession(){
		SqlSession session = local.get();
		if(session == null){
			session = factory.openSession();
			local.set(session);
		}
		return session;
	}
	
	
	public static void close(){
		SqlSession session = getSession();
		session.close();
		local.remove();
	}
	
	
}
