package com.itany.netClass.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private Integer id;
    private String context;
    private Timestamp createDate;
    private Integer status;
    private User user;
    private Resource resource;
    private Integer praiseCount;
    private Timestamp startdate;
    private Timestamp endDate;

    public Comment() {
    }

    public Comment(Integer id, String context, Timestamp createDate, Integer status, User user, Resource resource, Integer praiseCount, Timestamp startdate, Timestamp endDate) {
        this.id = id;
        this.context = context;
        this.createDate = createDate;
        this.status = status;
        this.user = user;
        this.resource = resource;
        this.praiseCount = praiseCount;
        this.startdate = startdate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
