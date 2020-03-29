package com.itany.netClass.controller;

import com.itany.mvc.annotation.Configuration;
import com.itany.mvc.config.ResourceHandlerRegistry;
import com.itany.mvc.config.WebConfigurer;

/**
 * 直接跳转jsp的配置
 * @author teacher
 * @date 2018-8-22
 */
@Configuration
public class MyConfig extends WebConfigurer {

	@Override
	public void addViewControllers(ResourceHandlerRegistry registry) {
		// 注册mvc-controller  即请求直接指向页面
		// 参数1 viewName
		// 参数2 url
		registry.addViewController("back_login", "/showLogin")
				.addViewController("back_Home", "/home")
				.addViewController("front_index", "/showIndex");
	}

}
