package com.itany.netClass.vo;

import com.itany.netClass.entity.User;

import java.util.Date;

/**
 * 供条件查询使用的-示例
 * @author teacher
 * @date 2018-8-22
 */
public class UserQuery extends User {

	public UserQuery() {
		super();
	}

	private String nicknameLike;
	private String emailLike;
	
	private Date createDateBegin;
	private Date createDateEnd;
	private Date loginDateBegin;
	private Date loginDateEnd;
	public String getNicknameLike() {
		return nicknameLike;
	}
	public void setNicknameLike(String nicknameLike) {
		this.nicknameLike = nicknameLike;
	}
	public String getEmailLike() {
		return emailLike;
	}
	public void setEmailLike(String emailLike) {
		this.emailLike = emailLike;
	}
	public Date getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public Date getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public Date getLoginDateBegin() {
		return loginDateBegin;
	}
	public void setLoginDateBegin(Date loginDateBegin) {
		this.loginDateBegin = loginDateBegin;
	}
	public Date getLoginDateEnd() {
		return loginDateEnd;
	}
	public void setLoginDateEnd(Date loginDateEnd) {
		this.loginDateEnd = loginDateEnd;
	}
	
}
