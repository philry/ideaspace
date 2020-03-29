package com.itany.netClass.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据正则验证
 * @author teacher
 * @date 2018-8-23 上午11:49:14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Regex {
	/**
	 * 正则表达式
	 * @author teacher
	 * @date 2018-8-23 下午1:59:37
	 * @return
	 */
	String value();
}
