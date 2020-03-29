package com.itany.nmms.controller;

import com.itany.mvc.annotation.Configuration;
import com.itany.mvc.config.ResourceHandlerRegistry;
import com.itany.mvc.config.WebConfigurer;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 15:24
 * Description:配置直接访问视图的命令
 * version:1.0
 */
//@Configuration表示当前类用于配置直接访问视图的请求
@Configuration
public class MyConfig  extends WebConfigurer {
    @Override
    public void addViewControllers(ResourceHandlerRegistry registry) {
        //注册请求
        //第一个参数表示你想要访问的视图是谁
        //第二参数表示你访问该视图的命令是什么
        //WEB-INF/pages/backend/login.jsp
        //WEB-INF/pages/--前缀
        //backend/login--视图名，即第一个参数所需要的数据
        //.jsp--后缀
        registry.addViewController("backend/login","/showLogin")
                .addViewController("backend/main","/showMain");
    }
}
