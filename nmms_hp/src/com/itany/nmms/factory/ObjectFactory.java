package com.itany.nmms.factory;

import com.itany.nmms.util.MyBatisUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static Map<String,Object> objs=new HashMap<String,Object>();
    static{
        try {
            BufferedReader br =null;
            br=new BufferedReader(new InputStreamReader(ObjectFactory.class.getClassLoader()
                                        .getResourceAsStream("beans.properties")));
            String line=null;
            while ((line=br.readLine())!=null){
                String[] arr =line.split("=");
                String key=arr[0];
                String value=arr[1];
                Class target =Class.forName(value);
                if(target.isInterface()){
                    objs.put(key,target);
                    continue;
                }
                objs.put(key,target.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("ObjectFactory初始化失败");
        }
    }

    public static Object getObject(String key){
        Object obj =objs.get(key);
        if(obj instanceof Class){
            return MyBatisUtil.getSession().getMapper((Class)obj);
        }
        return obj;
    }
}
