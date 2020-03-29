package com.itany.netClass.service.impl;

import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseTypeMapper;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeNotExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;

import java.util.ArrayList;
import java.util.List;

public class CourseTypeServiceImpl implements CourseTypeService {
    @Override
    public List<CourseType> findFirstCourse() {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        List<CourseType> list = courseTypeMapper.findFirstCourse();
        return list;
    }

    @Override
    public List<CourseType> findEnable() throws CourseTypeNotExistException {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        List<CourseType> types = new ArrayList<>();
        List<CourseType> courseTypes = courseTypeMapper.findAll();
        for (CourseType courseType : courseTypes) {
            if (Constant.STATUS_ENABLE == courseType.getStatus()) {
                types.add(courseType);
            }
        }
        if (types.isEmpty()) {
            throw new CourseTypeNotExistException("没有该课程类型");
        }
        return types;
    }

    @Override
    public List<CourseType> findSonCourseType(String id) {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        List<CourseType> list = new ArrayList<>();
        if (id != null && !"".equals(id)) {
            list = courseTypeMapper.findSonCourseType(Integer.parseInt(id));
        } else {
            list = courseTypeMapper.findFirstCourse();
        }
        return list;
    }

    @Override
    public void addCourseType(String typeName, String parentId, String id) {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        if (id != null && !"".equals(id)) {
            CourseType ct = courseTypeMapper.findById(Integer.parseInt(id));
            ct.setName(typeName);
            courseTypeMapper.updateCourseType(ct);
        } else {
            CourseType courseType = new CourseType();
            courseType.setName(typeName);
            if (parentId != null && !"".equals(parentId)) {
                courseType.setParentId(Integer.parseInt(parentId));
            }
            courseType.setStatus(0);
            courseTypeMapper.addType(courseType);
        }

    }

    @Override
    public List<CourseType> findParentCourseType(Integer id) {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        CourseType type = courseTypeMapper.findParentCourseType(id);
        Integer parentId = type.getParentId();
        List<CourseType> list = new ArrayList<CourseType>();
        if (parentId != null && !"".equals(parentId)) {
            list = courseTypeMapper.findSonCourseType(parentId);
        } else {
            list = courseTypeMapper.findFirstCourse();
        }
        return list;
    }

    @Override
    public void changeCourseTypeStatus(Integer id) {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        CourseType courseType = courseTypeMapper.findById(id);
        if (courseType.getStatus() == 0) {
            courseType.setStatus(1);
        } else {
            courseType.setStatus(0);
        }
        courseTypeMapper.updateCourseType(courseType);
    }

    @Override
    public CourseType findThisType(Integer id) {
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");
        CourseType courseType = courseTypeMapper.findById(id);
        return courseType;
    }


}
