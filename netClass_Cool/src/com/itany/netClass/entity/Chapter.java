package com.itany.netClass.entity;

import java.util.Date;

public class Chapter {
	private Integer id;
	private Integer courseId;
	private String title;
	private String info;
	private Date createDate;
	private Integer status;
	private Course course;
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Chapter{" +
				"id=" + id +
				", courseId=" + courseId +
				", course=" + course +
				", title='" + title + '\'' +
				", info='" + info + '\'' +
				", createDate=" + createDate +
				", status=" + status +
				'}';
	}
}
