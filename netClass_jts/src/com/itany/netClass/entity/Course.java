package com.itany.netClass.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Course implements Serializable {
    private Integer id;
    private String courseName;
    private String courseInfo;
    private String author;
    private String coverImageUrl;
    private Timestamp createDate;
    private Integer clickNumber;
    private Integer status;
    private Integer recommendationGrade;
    private CourseType courseType;

    //----------------------
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseInfo='" + courseInfo + '\'' +
                ", author='" + author + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", createDate=" + createDate +
                ", clickNumber=" + clickNumber +
                ", status=" + status +
                ", recommendationGrade=" + recommendationGrade +
                ", courseType=" + courseType +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommendationGrade() {
        return recommendationGrade;
    }

    public void setRecommendationGrade(Integer recommendationGrade) {
        this.recommendationGrade = recommendationGrade;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Course(Integer id, String courseName, String courseInfo, String author, String coverImageUrl, Timestamp createDate, Integer clickNumber, Integer status, Integer recommendationGrade, CourseType courseType, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.courseName = courseName;
        this.courseInfo = courseInfo;
        this.author = author;
        this.coverImageUrl = coverImageUrl;
        this.createDate = createDate;
        this.clickNumber = clickNumber;
        this.status = status;
        this.recommendationGrade = recommendationGrade;
        this.courseType = courseType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {

    }
}
