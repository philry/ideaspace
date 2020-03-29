package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.CourseType;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class CourseTypeServiceProxy implements CourseTypeService {
    @Override
    public List<CourseType> selectLastTypes() {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeServiceTarget");
        TransactionManager tran = ObjectFactory.getObject("transaction");
        List<CourseType> courseTypes = null;
        try {
            tran.beginTransaction();
            courseTypes = courseTypeService.selectLastTypes();
            tran.commit();
            return courseTypes;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }
}
