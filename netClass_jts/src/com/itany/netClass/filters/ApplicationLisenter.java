package com.itany.netClass.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 项目应用监听器
 * @author teacher
 * @date 2018-8-22
 */
@WebListener
public class ApplicationLisenter implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		//ServletContext application = context.getServletContext();
	}

}
