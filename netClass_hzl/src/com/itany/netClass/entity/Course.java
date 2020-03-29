package com.itany.netClass.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Course implements Serializable {
    private Integer id;
    private String name;
    private String author;
    private String courseInfo;//课程简介
    private String imageUrl;
    private Timestamp createDate;
    private Integer clickNum;
    private Integer status;
    private Integer recommmendGreade;
    private CourseType courseType;//课程类别

    public Course() {
    }

    public Course(Integer id, String name, String author, String courseInfo, String imageUrl, Timestamp createDate, Integer clickNum, Integer status, Integer recommmendGreade, CourseType courseType) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.courseInfo = courseInfo;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
        this.clickNum = clickNum;
        this.status = status;
        this.recommmendGreade = recommmendGreade;
        this.courseType = courseType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommmendGreade() {
        return recommmendGreade;
    }

    public void setRecommmendGreade(Integer recommmendGreade) {
        this.recommmendGreade = recommmendGreade;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}


