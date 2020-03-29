package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.Date;

public class Chapter implements Serializable {
    private Integer id;
    private Course course;
    private String title;
    private String info;
    private Date createDate;
    private Integer status;

    public Chapter() {
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", course=" + course +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }

    public Chapter(Integer id, Course course, String title, String info, Date createDate, Integer status) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.info = info;
        this.createDate = createDate;
        this.status = status;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
