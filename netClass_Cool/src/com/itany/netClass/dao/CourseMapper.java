package com.itany.netClass.dao;

import com.itany.netClass.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    /**
     * 添加课程
     * @param course
     */
    public void addCourse(Course course);

    /**
     * 查询所有课程
     * @return
     */
    public List<Course> findAll();

    /**
     * 根据课程名查询
     * @param courseName
     * @return
     */
    public List<Course> selectByName(@Param("course_name") String courseName);

    /**
     * 根据查询条件查询数据
     * @param course
     * @return
     */
    public List<Course> selectByData(Course course);

    /**
     * 根据id修改status属性
     * @param course
     */
    public void modifyStatusById(Course course);

    /**
     * 根据id修改其他数据
     * @param course
     */
    public void modifyById(Course course);

}
