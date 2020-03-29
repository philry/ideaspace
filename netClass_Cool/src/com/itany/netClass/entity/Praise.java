package com.itany.netClass.entity;

import java.io.Serializable;

public class Praise implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer commentid;
    private String createDate;


    @Override
    public String toString() {
        return "Praise{" +
                "id=" + id +
                ", userId=" + userId +
                ", commentid=" + commentid +
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

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Praise() {

    }
}
