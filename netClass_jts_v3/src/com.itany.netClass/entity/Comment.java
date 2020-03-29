package com.itany.netClass.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private Integer id;
    private String context;
    private Timestamp createDate;
    private User user;
    private Resource resource;
    private Integer status;
    private Integer praiseCount;


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", createDate=" + createDate +
                ", user=" + user +
                ", resource=" + resource +
                ", status=" + status +
                ", praiseCount=" + praiseCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Comment(Integer id, String context, Timestamp createDate, User user, Resource resource, Integer status, Integer praiseCount, Timestamp startDate, Timestamp endDate) {

        this.id = id;
        this.context = context;
        this.createDate = createDate;
        this.user = user;
        this.resource = resource;
        this.status = status;
        this.praiseCount = praiseCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Timestamp startDate;
    private Timestamp endDate;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Comment() {

    }
}
