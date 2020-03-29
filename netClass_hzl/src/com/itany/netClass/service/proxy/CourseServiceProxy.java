package com.itany.netClass.service.proxy;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.CourseExistException;
import com.itany.netClass.exception.CourseNotExistException;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.transaction.TransactionManager;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CourseServiceProxy implements CourseService {
    CourseService courseServiceTarget=ObjectFactory.getObject("courseServiceTarget");
    TransactionManager transaction=ObjectFactory.getObject("transaction");

    @Override
    public void insertCourse(String courseName, String courseInfo, String author, Timestamp timestamp,
                             Integer clickNum, Integer status, String recommendGrade, String courseTypeId,
                             List<CommonsMultipartFile> images, HttpSession session) throws FileUploadException, CourseExistException {
        transaction.beginTransaction();
        try {
            courseServiceTarget.insertCourse(courseName,courseInfo,author,timestamp,clickNum,status,
                    recommendGrade,courseTypeId,images,session);
            transaction.commit();
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw e;
        } catch (CourseExistException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Course> findAll() {
        transaction.beginTransaction();
        List<Course> courses = courseServiceTarget.findAll();
        transaction.commit();
        return courses;
    }

    @Override
    public Course findById(Integer id) throws CourseNotExistException {
        transaction.beginTransaction();
        Course course = null;
        try {
            course = courseServiceTarget.findById(id);
            transaction.commit();
        } catch (CourseNotExistException e) {
            e.printStackTrace();
            throw e;
        }
        return course;
    }

    @Override
    public void modifyCourse(Integer id,String courseName,String courseInfo,String author,Integer recommendGrade,
             Integer courseTypeId,List<CommonsMultipartFile> images,HttpSession session) throws FileUploadException {
        transaction.beginTransaction();
        try {
            courseServiceTarget.modifyCourse(id,courseName,courseInfo,author,recommendGrade,
                    courseTypeId,images,session);
            transaction.commit();
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void changeStatus(Integer id) {
        transaction.beginTransaction();
        courseServiceTarget.changeStatus(id);
        transaction.commit();
    }
}
