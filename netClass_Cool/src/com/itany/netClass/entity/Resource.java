package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable{
	private Integer id;
	private String title ;
	private String path;
	private String coverImageUrl;
	private String originalName;
	private Integer clickCount;
	private Integer fileSize;
	private String fileType;
	private Date createDate;
	private Integer costType;
	private Integer costNumber;
	private Integer chapterId;
	private Integer status;
	private Integer userId;
	private Chapter chapter;
	private User user;
	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getCostType() {
		return costType;
	}
	public void setCostType(int costType) {
		this.costType = costType;
	}
	public int getCostNumber() {
		return costNumber;
	}
	public void setCostNumber(int costNumber) {
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", title=" + title + ", path=" + path
				+ ", coverImageUrl=" + coverImageUrl + ", originalName="
				+ originalName + ", clickCount=" + clickCount + ", fileSize="
				+ fileSize + ", fileType=" + fileType + ", createDate="
				+ createDate + ", costType=" + costType + ", costNumber="
				+ costNumber + ", user=" + user + ", chapter=" + chapter
				+ ", status=" + status + "]";
	}

	
	
}
