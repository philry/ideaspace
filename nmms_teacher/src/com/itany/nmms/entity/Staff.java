package com.itany.nmms.entity;

import java.io.Serializable;
import java.util.Date;

public class Staff implements Serializable{

	private Integer staffId;
	private String staffName;
	private String loginName;
	private String password;
	private String phone;
	private String email;
	private Integer deptId;
	//员工角色，值有两个,1001:表示系统管理员，1002：普通管理员
	private String role;
	//是否有效
	private Integer isValid;
	//创建时间
	private Date createDate;
	//创建人
	private Integer createStaffId;
	private Dept dept;
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCreateStaffId() {
		return createStaffId;
	}
	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}
}
