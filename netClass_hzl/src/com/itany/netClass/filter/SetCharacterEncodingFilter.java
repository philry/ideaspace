package com.itany.netClass.filter;

import com.itany.netClass.constant.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
		System.out.println("getServletPath="+((HttpServletRequest)request).getServletPath());
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
