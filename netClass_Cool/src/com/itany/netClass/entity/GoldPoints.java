package com.itany.netClass.entity;

import java.io.Serializable;

public class GoldPoints implements Serializable {


    private Integer id;
    private Integer userId;
    private Integer pointCount;
    private Integer goldCount;
    private String info;
    private String createDate;

    @Override
    public String toString() {
        return "GoldPoints{" +
                "id=" + id +
                ", userId=" + userId +
                ", pointCount=" + pointCount +
                ", goldCount=" + goldCount +
                ", info='" + info + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public GoldPoints() {

    }
}
