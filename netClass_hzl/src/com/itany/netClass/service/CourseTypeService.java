
package com.itany.netClass.service;

import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeNotExistException;

import java.util.List;

import java.util.List;

public interface CourseTypeService {

    public List<CourseType> findFirstCourse();

    public List<CourseType> findEnable() throws CourseTypeNotExistException;

    public List<CourseType> findSonCourseType(String id);

    public void addCourseType(String typeName, String parentId,String id);

    public List<CourseType> findParentCourseType(Integer id);

    public void changeCourseTypeStatus(Integer id);

    public CourseType findThisType(Integer id);

}

