package com.itany.netClass.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Praise implements Serializable {
    private Integer id;
    private User user;
    private Comment comment;
    private Timestamp createDate;

    @Override
    public String toString() {
        return "Praise{" +
                "id=" + id +
                ", user=" + user +
                ", comment=" + comment +
                ", createDate=" + createDate +
                '}';
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Praise(Integer id, User user, Comment comment, Timestamp createDate) {

        this.id = id;
        this.user = user;
        this.comment = comment;
        this.createDate = createDate;
    }

    public Praise() {

    }
}
