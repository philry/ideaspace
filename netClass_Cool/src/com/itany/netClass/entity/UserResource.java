package com.itany.netClass.entity;

import java.io.Serializable;

public class UserResource implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer resourceId;
    private String createDate;
    private String updateDate;

    @Override
    public String toString() {
        return "UserResource{" +
                "id=" + id +
                ", userId=" + userId +
                ", resourceId=" + resourceId +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
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

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public UserResource() {

    }
}
