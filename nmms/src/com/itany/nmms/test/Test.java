package com.itany.nmms.test;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/2 9:44
 * Description:
 * version:1.0
 */
public class Test {
    public static void main(String[] args) {
        String s = "999991";
//        String s2 = Integer.parseInt(s)+1+"";
//        s2 = "00000"+s2;
//        s2 = s2.substring(s2.length()-6);
//        System.out.println(s2);

        String s2 = String.format("%06d",Integer.parseInt(s)+1);
        System.out.println(s2);

    }
}
