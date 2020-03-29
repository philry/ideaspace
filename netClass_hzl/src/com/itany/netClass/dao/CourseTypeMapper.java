package com.itany.netClass.dao;

import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;

import java.util.List;

public interface CourseTypeMapper {
    /**
     * 添加
     *
     * @param type
     */
    public void addType(CourseType type);

    /**
     * 查找全部
     *
     * @return
     */
    public List<CourseType> findAll();

    /**
     * 查找一级课程分类
     *
     * @return
     */
    public List<CourseType> findFirstCourse();

    /**
     * 查找子课程分类
     *
     * @param id
     * @return
     */
    public List<CourseType> findSonCourseType(Integer id);

    /**
     * 查找父类课程分类
     *
     * @param id
     * @return
     */
    public CourseType findParentCourseType(Integer id);

    /**
     * 修改课程分类
     *
     * @param courseType
     */
    public void updateCourseType(CourseType courseType);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public CourseType findById(Integer id);


}
