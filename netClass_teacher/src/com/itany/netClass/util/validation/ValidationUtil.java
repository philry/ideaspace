package com.itany.netClass.util.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.itany.netClass.util.validation.annotation.Length;
import com.itany.netClass.util.validation.annotation.NotEmpty;
import com.itany.netClass.util.validation.annotation.Regex;

/**
 * 对象数据验证工具
 * @author teacher
 * @date 2018-8-23 上午11:36:23
 */
public class ValidationUtil {
	
	/**
	 * 验证对象种所有属性的值是否符合要求<br>
	 * 不能验证属性的类型是否符合要求
	 * @author teacher
	 * @date 2018-8-23 上午11:37:49
	 * @param t
	 * @exception RuntimeException 验证不通过会抛出此异常
	 */
	public static <T> void valid(T t) throws RuntimeException {
		Class<?> c = t.getClass();
		
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Annotation[] anns = f.getAnnotations();
			for (Annotation an : anns) {
				if(an instanceof NotEmpty){//是否是此注解
					Object value = getValue(f, t);
					if(null == value){
						throw new RuntimeException("Field["+f.getName()+"] is null.");
					}
					if(value instanceof String && "".equals(((String) value).trim())){
						throw new RuntimeException("Field["+f.getName()+"] is empty.");
					}
				}
				if(an instanceof Length){//是否是此注解
					Object value = getValue(f, t);
					Length lenAn = (Length) an;
					if(null != value){
						if(value instanceof String){//null,进不来,不是String类型
							String s = (String) value;
							int len = s.length();
							if(lenAn.begin() > len || lenAn.end() < len){
								throw new RuntimeException("Field["+f.getName()+"] is out of range ["+lenAn.begin()+"~"+lenAn.end()+"].");
							}
						}else if(value instanceof Number){//数值类型
							//Object转对应的数值类型
							if(value instanceof Integer){//int
								int val = (Integer) value;
								if(lenAn.begin() > val || lenAn.end() < val){
									throw new RuntimeException("Field["+f.getName()+"] is out of range ["+lenAn.begin()+"~"+lenAn.end()+"].");
								}
							}
						}
					}else{
						throw new RuntimeException("Field["+f.getName()+"] is null.");
					}
				}
				if(an instanceof Regex){//是否是此注解
					Regex regexAn = (Regex)an;
					String regex = regexAn.value();//""
					Object value = getValue(f, t);
					if(value instanceof String){
						String s = (String)value;
						if(!s.matches(regex)){
							throw new RuntimeException("Field["+f.getName()+"] is not matches the given regular expression. regular expression = ["+regex+"]");
						}
					}else if(null == value){
						throw new RuntimeException("Field["+f.getName()+"] is null.");
					}
				}
			}
		}
	}
	
	private static <T> Object getValue(Field f, T t){
		Object val = null;
		//正常是通过调用get方法获取属性的值
		try {
			f.setAccessible(true);
			Object value = f.get(t);
//			Class<?> type = f.getType();//属性值的类型
//			System.out.println("value="+value);
//			System.out.println("type="+type);
			val = value;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return val;
	}
	
}
