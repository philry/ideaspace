package com.itany.netClass.service.impl;

import com.itany.netClass.dao.CourseTypeMapper;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;

import java.util.List;

public class CourseTypeServiceImpl implements CourseTypeService {
    @Override
    public List<CourseType> selectLastTypes() {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        List<CourseType> courseTypes = courseTypeMapper.selectLastTypes();
        return courseTypes;
    }
}
