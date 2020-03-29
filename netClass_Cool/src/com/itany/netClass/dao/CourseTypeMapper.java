package com.itany.netClass.dao;


import com.itany.netClass.entity.CourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTypeMapper {
    /**
     * 添加课程类型
     * @param type
     */
    public void addType(CourseType type);

    /**
     * 根据类型名字查询类型对象
     * @param typeName
     * @return
     */
    public List<CourseType> selectByName(String typeName);

    /**
     * 根据父级id查询
     * @param parentId
     * @return
     */
    public List<CourseType> selectByParentId(@Param("parentId") Integer parentId);

    /**
     * 查询没有父级id的类型
     * @return
     */
    public List<CourseType> selectAllAndParentIdIsNull();

    /**
     * 查询所有三级类型
     * @return
     */
    public List<CourseType> selectAllThird();



}
