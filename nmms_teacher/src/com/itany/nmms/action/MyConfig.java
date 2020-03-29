package com.itany.nmms.action;

import com.itany.mvc.annotation.Configuration;
import com.itany.mvc.config.ResourceHandlerRegistry;
import com.itany.mvc.config.WebConfigurer;

//@Configuration表示配置直接访问视图的请求
@Configuration
public class MyConfig extends WebConfigurer {

	@Override
	public void addViewControllers(ResourceHandlerRegistry registry) {
		//第一个参数:表示当前需要访问视图是哪一个
		//由于配置了前缀与后缀,因此我们需要省略前缀与后缀的路径
		//例如:当前要访问的页面为:/WEB-INF/pages/backend/login.jsp
		//由于/WEB-INF/pages/为前缀,.jsp为后缀
		//因此此时的视图名就是:backend/login
		//第二个参数:你访问该视图时的命令
		//相当于Servlet中的url-pattern
		registry.addViewController("backend/login", "/showLogin")
				.addViewController("backend/main", "/showMain");
	}
	
	

}
