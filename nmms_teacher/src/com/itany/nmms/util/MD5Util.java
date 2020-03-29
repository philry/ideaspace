package com.itany.nmms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	
	public static String md5(String s){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(s.getBytes());
//			return new String(b);
			BASE64Encoder ecoder = new BASE64Encoder();
			return ecoder.encode(b);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
	
}
