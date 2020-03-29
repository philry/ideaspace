package com.itany.netClass.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.itany.netClass.constant.Constant;

/**
 * 字符集过滤器
 * @author teacher
 * @date 2018-8-22
 */
@WebFilter(
	filterName="encodingFilter", 
	urlPatterns="/*",
	initParams={ @WebInitParam(name="encoding",value="UTF-8") }
	)
public class SetCharacterEncodingFilter implements Filter{
    
	private String encoding;
	
	@Override
	public void destroy() {
		this.encoding = null;
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
		//一般处理方式
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		response.setContentType("application/json");
		chain.doFilter(request, response);
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	    if(this.encoding == null){
	    	this.encoding = Constant.FILTER_CHARSET_UTF8;
	    }
    }

}
