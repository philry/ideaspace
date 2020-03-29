package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeNotExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class CourseTypeServiceProxy implements CourseTypeService {
    TransactionManager tran = ObjectFactory.getObject("transaction");
    CourseTypeService courseTypeServiceTarget = ObjectFactory.getObject("courseTypeServiceTarget");

    @Override
    public List<CourseType> findFirstCourse() {
        try {
            tran.beginTransaction();
            List<CourseType> list = courseTypeServiceTarget.findFirstCourse();
            tran.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<CourseType> findEnable() throws CourseTypeNotExistException {
        tran.beginTransaction();
        List<CourseType> courseTypes = null;
        try {
            courseTypes = courseTypeServiceTarget.findEnable();
            tran.commit();
        } catch (CourseTypeNotExistException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
        return courseTypes;
    }

    @Override
    public List<CourseType> findSonCourseType(String id) {
        try {
            tran.beginTransaction();
            List<CourseType> list = courseTypeServiceTarget.findSonCourseType(id);
            tran.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void addCourseType(String typeName, String parentId, String id) {
        try {
            tran.beginTransaction();
            courseTypeServiceTarget.addCourseType(typeName, parentId, id);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<CourseType> findParentCourseType(Integer id) {
        try {
            tran.beginTransaction();
            List<CourseType> list = courseTypeServiceTarget.findParentCourseType(id);
            tran.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void changeCourseTypeStatus(Integer id) {
        try {
            tran.beginTransaction();
            courseTypeServiceTarget.changeCourseTypeStatus(id);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public CourseType findThisType(Integer id) {
        try {
            tran.beginTransaction();
            CourseType courseType = courseTypeServiceTarget.findThisType(id);
            tran.commit();
            return courseType;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }
}
