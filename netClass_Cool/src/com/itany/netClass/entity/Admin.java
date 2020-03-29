package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable{

    private static final long serialVersionUID = 6943055294246852514L;
    private Integer id;
    @Override
    public String toString() {
        return "Admin [id=" + id + ", loginname=" + loginname + ", nickname="
                + nickname + ", headerimageurl=" + headerimageurl
                + ", password=" + password + ", role=" + role + ", email="
                + email + ", loginDate=" + loginDate + ", createDate="
                + createDate + ", status=" + status + "]";
    }
    private String loginname;
    private String nickname;
    private String headerimageurl;
    private String password;
    private String role;
    private String email;
    private Date loginDate;
    private Date createDate;
    private Integer status;
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLoginname() {
        return loginname;
    }
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getHeaderimageurl() {
        return headerimageurl;
    }
    public void setHeaderimageurl(String headerimageurl) {
        this.headerimageurl = headerimageurl;
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

}