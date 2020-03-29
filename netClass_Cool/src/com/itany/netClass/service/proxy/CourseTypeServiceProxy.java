package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNoExistsException;
import com.itany.netClass.exception.ParameterException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;


public class CourseTypeServiceProxy implements CourseTypeService {
    @Override
    public void addType(String typeName, String parentId) throws CourseTypeExistsException, ParameterException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseTypeService typeService = ObjectFactory.getObject("courseTypeServiceTarget");

        try {
            tran.beginTransaction();
            typeService.addType(typeName,parentId);
            tran.commit();
        } catch (CourseTypeExistsException e) {
            tran.rollback();
            throw e;
        } catch (ParameterException e) {
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<CourseType> findFirstLevel() throws CourseTypeNoExistsException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseTypeService typeService = ObjectFactory.getObject("courseTypeServiceTarget");
        try {
            tran.beginTransaction();
            List<CourseType> types = typeService.findFirstLevel();
            tran.commit();
            return types;
        } catch (CourseTypeNoExistsException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<CourseType> findAll(String parentId) throws ParameterException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseTypeService typeService = ObjectFactory.getObject("courseTypeServiceTarget");
        try {
            tran.beginTransaction();
            List<CourseType> types = typeService.findAll(parentId);
            tran.commit();
            return types;
        } catch (ParameterException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<CourseType> findAllThird() {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseTypeService typeService = ObjectFactory.getObject("courseTypeServiceTarget");
        tran.beginTransaction();
        List<CourseType> types = typeService.findAllThird();
        tran.commit();
        return types;
    }

//    @Override
//    public List<CourseType> findAll() throws CourseTypeNoExistsException {
//        TransactionManager tran = ObjectFactory.getObject("tran");
//        CourseTypeService typeService = ObjectFactory.getObject("courseTypeServiceTarget");
//        try {
//            tran.beginTransaction();
//            List<CourseType> types = typeService.findAll();
//            tran.commit();
//            return types;
//        } catch (CourseTypeNoExistsException e) {
//            e.printStackTrace();
//            tran.rollback();
//            throw e;
//        }
//    }
}
