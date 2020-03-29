package com.itany.netClass.dao;

import com.itany.netClass.entity.Course;

import java.util.List;

public interface CourseMapper {

    public List<Course> selectCoursesOfTopThree();

    /**
     * 在后台课程管理界面显示所有课程和课程信息
     * @return
     */
    public List<Course> selectByParams(Course course);

    public void modifyStatus(Integer id, Integer status);

    public void addCourse(Course course);

    public void modifyCourse(Course course);
}
