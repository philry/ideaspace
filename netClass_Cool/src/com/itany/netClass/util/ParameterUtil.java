package com.itany.netClass.util;

import com.itany.netClass.constant.Constant;

public class ParameterUtil {

    public static boolean isNull(String s){

       if(null==s || "".equals(s)){
           return true;
       }
       return false;
    }

    public static Integer getPageNo(String no){
        if(ParameterUtil.isNull(no)||"null".equals(no)){
            return new Integer(Constant.PAGE_NO);
        }
        return Integer.parseInt(no);
    }
    //获取正确格式的页数
    public static Integer getPageSize(String size){
        if(ParameterUtil.isNull(size)||"null".equals(size)){
            return new Integer(Constant.PAGE_SIZE);
        }
        return Integer.parseInt(size);
    }
}
