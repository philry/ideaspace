package com.itany.netClass.entity;

import java.util.Date;

public class GoldPoints {

    private  Integer id;
    private  User user;
    private  Integer pointCount;
    private Integer  goldCount;
    private String info;
    private Date createDate;

    @Override
    public String toString() {
        return "GoldPoints{" +
                "id=" + id +
                ", user=" + user +
                ", pointCount=" + pointCount +
                ", goldCount=" + goldCount +
                ", info='" + info + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public GoldPoints() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
    }

    public Integer getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(Integer goldCount) {
        this.goldCount = goldCount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
