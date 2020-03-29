package com.itany.netClass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netClass.util.validation.annotation.Length;
import com.itany.netClass.util.validation.annotation.NotEmpty;
import com.itany.netClass.util.validation.annotation.Regex;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author teacher
 * @date 2018-8-17
 */
public class User implements Serializable {
	private Integer id;
	@NotEmpty
	@Length(begin=1,end=8)
	private String loginName;
	private String nickname;
	private String headerImageUrl;
	@Regex("\\w{6,8}")
	private String password;
	private String role;
	private String email;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date loginDate;
	private Date createDate;
	@NotEmpty
	@Length(begin=0,end=1)
	private Integer status;
	public User() {
		super();
	}

	//	public User(Integer id, String loginName, String nickname,
//			String headerImageUrl, String password, String role, String email,
//			Date loginDate, Date createDate, Integer status) {
//		super();
//		this.id = id;
//		this.loginName = loginName;
//		this.nickname = nickname;
//		this.headerImageUrl = headerImageUrl;
//		this.password = password;
//		this.role = role;
//		this.email = email;
//		this.loginDate = loginDate;
//		this.createDate = createDate;
//		this.status = status;
//	}
	public Integer getId() {
		return id;
	}
	/**
	 * 设置user对象的id主键
	 * @author teacher
	 * @date 2018-8-17 上午9:22:17
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 *
	 * @author teacher
	 * @date 2018-8-17 上午9:22:33
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeaderImageUrl() {
		return headerImageUrl;
	}
	public void setHeaderImageUrl(String headerImageUrl) {
		this.headerImageUrl = headerImageUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	private String rePassword;

	public User(Integer id, String loginName, String nickname, String headerImageUrl, String password, String role, String email, Date loginDate, Date createDate, Integer status, String rePassword) {
		this.id = id;
		this.loginName = loginName;
		this.nickname = nickname;
		this.headerImageUrl = headerImageUrl;
		this.password = password;
		this.role = role;
		this.email = email;
		this.loginDate = loginDate;
		this.createDate = createDate;
		this.status = status;
		this.rePassword = rePassword;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", loginName='" + loginName + '\'' +
				", nickname='" + nickname + '\'' +
				", headerImageUrl='" + headerImageUrl + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				", email='" + email + '\'' +
				", loginDate=" + loginDate +
				", createDate=" + createDate +
				", status=" + status +
				", rePassword='" + rePassword + '\'' +
				'}';
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
}
