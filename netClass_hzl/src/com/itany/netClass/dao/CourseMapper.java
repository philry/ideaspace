package com.itany.netClass.dao;

import com.itany.netClass.entity.Course;

import java.util.List;

public interface CourseMapper {
    /**
     * 添加课程信息
     * @param course
     */
    public void insertCourse(Course course);

    public List<Course> findAll();

    public void updateCourse(Course course);

    public Course findById(Integer id);

    public Course findByName(String name);

    public List<Course>  selectTopThree();
}
