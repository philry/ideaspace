package com.itany.netClass.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证字符串数据的长度<br>
 * 或者验证数值的范围
 * @author teacher
 * @date 2018-8-23 上午11:51:52
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
	int begin();
	int end();
}
