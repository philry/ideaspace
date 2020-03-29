package com.itany.netClass.service.proxy;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.CourseExistsException;
import com.itany.netClass.exception.ParameterException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.transaction.TransactionManager;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CourseServiceProxy implements CourseService {
    @Override
    public List<Course> findAll() {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseService courseService = ObjectFactory.getObject("courseServiceTarget");

        tran.beginTransaction();
        List<Course> courses = courseService.findAll();
        tran.commit();
        return courses;
    }

    @Override
    public void addCourse(String courseName, String courseInfo, String author, String recommendation, String courseTypeId, HttpSession session, List<CommonsMultipartFile> files) throws CourseExistsException, FileUploadException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseService courseService = ObjectFactory.getObject("courseServiceTarget");
        try {
            tran.beginTransaction();
            courseService.addCourse(courseName,courseInfo,author,recommendation,courseTypeId,session,files);
            tran.commit();
        } catch (CourseExistsException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        } catch (FileUploadException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<Course> findByData(String author, String courseName, String status,
                                   String courseTypeId, String startDate, String endDate) throws ParameterException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseService courseService = ObjectFactory.getObject("courseServiceTarget");

        try {
            tran.beginTransaction();
            List<Course> courses = courseService.findByData(author, courseName, status, courseTypeId, startDate, endDate);
            tran.commit();
            return courses;
        } catch (ParameterException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyStatusById(String id, String status) throws ParameterException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseService courseService = ObjectFactory.getObject("courseServiceTarget");
        try {
            tran.beginTransaction();
            courseService.modifyStatusById(id,status);
            tran.commit();
        } catch (ParameterException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyById(Course course,HttpSession session,List<CommonsMultipartFile> files) throws FileUploadException, CourseExistsException {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CourseService courseService = ObjectFactory.getObject("courseServiceTarget");

        try {
            tran.beginTransaction();
            courseService.modifyById(course,session,files);
            tran.commit();
        } catch (FileUploadException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        } catch (CourseExistsException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }


}
