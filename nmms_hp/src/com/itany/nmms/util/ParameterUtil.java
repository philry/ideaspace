package com.itany.nmms.util;

public class ParameterUtil {
    public static boolean isNull(String s){
        if(s==null||"".equals(s)){
            return true;
        }
        return false;
    }
    public static boolean isNull(Integer s){
        if(s==null||"".equals(s)){
            return true;
        }
        return false;
    }
}
