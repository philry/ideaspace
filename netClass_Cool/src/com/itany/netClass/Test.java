package com.itany.netClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.User;
import com.itany.netClass.util.MyBatisUtil;

/**
 * 测试代码
 * @author teacher
 * @date 2018-8-22
 */
public class Test {

	public static void main(String[] args) {
		
		//functionTest("");
		
		User user = new User(
				null, "loginName111", 
				"nickname111", null, 
				"111", "admin", 
				"email", null, 
				new Date(), 0);
		procedureTest(user);
		
		//testProcedure(user);
		System.out.println("getId=" + user.getId());
		
	}
	
	public static void testProcedure(User user) {
		Connection conn = null;
		CallableStatement call = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.7.8:1521:orcl", "wbs18051", "wbs18051");
			//预定义函数的调用格式
			call = conn.prepareCall("{call addUser (?,?,?,?,?,?,?,?) }");
			//声明函数各个部分的类型和参数说明
			//设置普通参数
			call.setString(1,user.getLoginName());	
			call.setString(2,user.getNickname());
			call.setString(3,user.getPassword());
			call.setString(4,user.getRole());
			call.setString(5,user.getEmail());
			call.setDate(6, new java.sql.Date(user.getCreateDate().getTime()));
			call.setInt(7, user.getStatus());
			//注册返回参：游标
//			call.registerOutParameter(5,OracleTypes.CURSOR);
			
			//注册返回参：id
			call.registerOutParameter(8,Types.INTEGER);
			call.execute();
			
			int id = call.getInt(8);
			user.setId(id);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			if(null != call){
				try {
					call.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(null != conn){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void functionTest(String nickname) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			Integer userCount = userMapper.getUserCount(nickname);
			System.out.println("userCount=" + userCount);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		/*
		create or replace function getUserCount(nickname in varchar2) return number
		is
			v_count number(32);
			v_name varchar2(40);
		begin
			v_name:='%'||nickname||'%';
			select count(1) into v_count from t_user where nickname like v_name;
			return v_count;
		end;
		*/
	}
	
	public static void procedureTest(User user) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insertReturnId(user);
			System.out.println("id=" + user.getId());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		/*
		create or replace procedure addUser (
			loginName in varchar2, 
			nickname in varchar2, 
			password in varchar2, 
			role in varchar2, 
			email in varchar2, 
			createDate in date, 
			status in number, 
			userId out number
		)
		is 
		begin
			select user_seq.nextval into userId from dual;
			insert into t_user (id, login_name, nickname, password, role, email, create_date, status) 
			values 
			(user_seq.currval,loginName,nickname,password,role,email,createDate,status);
		end;
		*/
	}
	
}
