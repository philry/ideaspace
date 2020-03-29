package com.itany.nmms.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.itany.nmms.util.MyBatisUtil;

public class ObjectFactory {
	
	
	private static Map<String, Object> objs = new HashMap<String, Object>();
			
	
	static{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
						new InputStreamReader(
								ObjectFactory.class
											 .getClassLoader()
											 .getResourceAsStream("objects.properties")));
			String s = null;
			while((s=reader.readLine()) != null){
				String[] arr = s.split("=");
				String key = arr[0];
				String value = arr[1];
				Class target = Class.forName(value);
				if(target.isInterface()){
					objs.put(key, target);
					continue;
				}
				objs.put(key, target.newInstance());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("ObjectFactory初始化失败");
		}
	}
	
	public static Object getObject(String key){
		Object obj = objs.get(key);
		if(obj instanceof Class){
			return MyBatisUtil.getSession().getMapper((Class)obj);
		}
		return obj;
	}

}
