package com.itany.netClass.entity;

import java.util.Date;

public class Resource {
    private Integer id;
    private String title;
    private String path;
    private String coverImageUrl;
    private String originalName;
    private String fileSize;
    private String fileType;
    private Integer clickCount;
    private Date createDate;
    private Integer costType;
    private Integer costNumber;
    private User user;
    private Chapter chapter;
    private Integer status;

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", originalName='" + originalName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileType='" + fileType + '\'' +
                ", clickCount=" + clickCount +
                ", createDate=" + createDate +
                ", costType=" + costType +
                ", costNumber=" + costNumber +
                ", user=" + user +
                ", chapter=" + chapter +
                ", status=" + status +
                '}';
    }

    public Resource(Integer id, String title, String path, String coverImageUrl, String originalName, String fileSize, String fileType, Integer clickCount, Date createDate, Integer costType, Integer costNumber, User user, Chapter chapter, Integer status) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.coverImageUrl = coverImageUrl;
        this.originalName = originalName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.clickCount = clickCount;
        this.createDate = createDate;
        this.costType = costType;
        this.costNumber = costNumber;
        this.user = user;
        this.chapter = chapter;
        this.status = status;
    }

    public Resource() {

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public Integer getCostNumber() {
        return costNumber;
    }

    public void setCostNumber(Integer costNumber) {
        this.costNumber = costNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}