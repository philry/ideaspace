package com.itany.netClass.service;

import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNoExistsException;
import com.itany.netClass.exception.ParameterException;

import java.util.List;

public interface CourseTypeService {
    /**
     * 添加新的课程类型
     * @param typeName
     * @param parentId
     */
    public void addType(String typeName, String parentId) throws CourseTypeExistsException, ParameterException;

//    /**
//     * 查询所有课程类型
//     * @return
//     */
//    public List<CourseType> findAll() throws CourseTypeNoExistsException;

    /**
     * 查询一级类型
     * @return
     */
    public List<CourseType> findFirstLevel() throws CourseTypeNoExistsException;

    /**
     * 根据父级id查询所有
     * @param parentId
     * @return
     */
    public List<CourseType> findAll(String parentId) throws ParameterException;

    /**
     * 查询所有三级类型
     * @return
     */
    public List<CourseType> findAllThird();


}
