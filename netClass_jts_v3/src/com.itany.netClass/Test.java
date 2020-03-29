package com.itany.netClass;

import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.User;
import com.itany.netClass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试代码
 * @author teacher
 * @date 2018-8-22
 */
public class Test {

	public static void main(String[] args) {

		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
            Timestamp timestamp = new Timestamp(format.parse(date).getTime());
            System.out.println(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		User user = new User(
//				null, "loginName111",
//				"nickname111", null,
//				"111", "admin",
//				"email", null,
//				new Date(), 0);
//		procedureTest(user);
		
		//testProcedure(user);
//		System.out.println("getId=" + user.getId());
		
	}
	

	
}
