package com.itany.nmms.util;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/1 10:31
 * Description:
 * version:1.0
 */
public class ParameterUtil {

    public static boolean isNull(String s){
        if(s == null || "".equals(s)){
            return true;
        }
        return false;
    }

}
