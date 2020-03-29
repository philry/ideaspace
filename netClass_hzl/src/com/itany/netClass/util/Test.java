package com.itany.netClass.util;

import com.itany.netClass.entity.User;
import com.itany.netClass.util.validation.ValidationUtil;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		
//		System.out.println(get() instanceof String);
//		System.out.println(get() instanceof Number);
//		System.out.println(get() instanceof Integer);
		
		User t = new User(
				1, 
				"loginNam", //1~8
				"nickname", 
				"headerImageUrl", 
				"123456", //6~8
				"role", 
				"email", 
				new Date(), 
				new Date(), 
				11//Constant.STATUS_ENABLE // notEmpty
				); 
		
		try {
			ValidationUtil.valid(t);
			System.out.println("验证通过");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public static Object get(){
		int i = 1;
		return i;
	}
	
}
