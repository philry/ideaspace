package com.itany.netClass.service.impl;

import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseTypeMapper;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNoExistsException;
import com.itany.netClass.exception.ParameterException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.ParameterUtil;

import java.util.List;

public class CourseTypeServiceImpl implements CourseTypeService {
    @Override
    public void addType(String typeName, String parentId) throws CourseTypeExistsException, ParameterException {
        //添加新的课程类型，需要判断是否重复
        CourseTypeMapper typeMapper = ObjectFactory.getObject("courseTypeMapper");

        CourseType type = new CourseType();

        //判断名字是否存在
        List<CourseType> types = typeMapper.selectByName(typeName);

        if(!types.isEmpty()){
            throw new CourseTypeExistsException("该课程名字已经存在");
        }
        type.setTypeName(typeName);
        type.setParentId(ParameterUtil.isNull(parentId)?null:Integer.parseInt(parentId));
        type.setStatus(Constant.STATUS_ENABLE);

        typeMapper.addType(type);
    }

    @Override
    public List<CourseType> findFirstLevel() throws CourseTypeNoExistsException {
        CourseTypeMapper typeMapper = ObjectFactory.getObject("courseTypeMapper");
        List<CourseType> types = typeMapper.selectAllAndParentIdIsNull();
        if(types.isEmpty()){
            throw new CourseTypeNoExistsException("课程类型为零");
        }
        return types;
    }

    @Override
    public List<CourseType> findAll(String parentId) throws ParameterException {
        CourseTypeMapper typeMapper = ObjectFactory.getObject("courseTypeMapper");

        try {
            List<CourseType> types = typeMapper.selectByParentId(ParameterUtil.isNull(parentId)?null:Integer.parseInt(parentId));
            return types;
        } catch (NumberFormatException e) {
            throw new ParameterException("参数有误");
        }
    }

    @Override
    public List<CourseType> findAllThird() {
        CourseTypeMapper typeMapper = ObjectFactory.getObject("courseTypeMapper");
        List<CourseType> types = typeMapper.selectAllThird();
        System.out.println(types.size()+"000000");
        return types;
    }

//    @Override
//    public List<CourseType> findAll() throws CourseTypeNoExistsException {
//        CourseTypeMapper typeMapper = ObjectFactory.getObject("courseTypeMapper");
//        //查询一级类型
//        List<CourseType> types = typeMapper.selectByIdAndParentIdIsNull();
//        if(types.isEmpty()){
//            throw new CourseTypeNoExistsException("课程类型为零");
//        }
//        //递归查询二级和三级的
//        findChildTypes(types);
//        return types;
//    }
//
//    //递归查询所有的二级三级类型
//    public void findChildTypes(List<CourseType> list){
//        CourseTypeMapper typeMapper = ObjectFactory.getObject("courseTypeMapper");
//        for(CourseType type:list){
//            List<CourseType> types = typeMapper.selectByParentId(type.getId());
//            if(!types.isEmpty()){
//                type.setChildTypes(types);
//                findChildTypes(types);
//            }
//
//        }
//    }

}
