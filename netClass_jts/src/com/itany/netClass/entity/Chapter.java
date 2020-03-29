package com.itany.netClass.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Chapter implements Serializable {
    private Integer id;
    private String title;
    private String info;
    private Timestamp createDate;
    private Integer status;
    private Course course;
    private Resource resource;

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                ", course=" + course +
                ", resource=" + resource +
                '}';
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Chapter(Integer id, String title, String info, Timestamp createDate, Integer status, Course course) {

        this.id = id;
        this.title = title;
        this.info = info;
        this.createDate = createDate;
        this.status = status;
        this.course = course;
    }

    public Chapter() {

    }
}
