package com.itany.netClass.service.proxy;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.exception.ParamsHavingNullException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.transaction.TransactionManager;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CourseServiceProxy implements CourseService {

    CourseService courseService=ObjectFactory.getObject("courseServiceTarget");
    TransactionManager tran = ObjectFactory.getObject("transaction");
    @Override
    public List<Course> selectCoursesOfTopThree() {

        List<Course> courses = null;
        try {
            tran.beginTransaction();
            courses = courseService.selectCoursesOfTopThree();
            tran.commit();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<Course> selectByParams(Course course) {
        List<Course> courses = null;
        try {
            tran.beginTransaction();
            courses = courseService.selectByParams(course);
            tran.commit();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyStatus(Integer id, Integer status) {
        try {
            tran.beginTransaction();
            courseService.modifyStatus(id,status);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void addCourse(String courseName, String courseInfo, String author, String courseTypeId, CommonsMultipartFile file, HttpSession session) throws FileUploadException, ParamsHavingNullException {
        try {
            tran.beginTransaction();
            courseService.addCourse(courseName,courseInfo,author,courseTypeId,file,session);
            tran.commit();
        } catch (FileUploadException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        } catch (ParamsHavingNullException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyCourse(String courseId, String courseName, String courseInfo, String author, String courseTypeId, String courseImageUrl, CommonsMultipartFile file, HttpSession session) throws ParamsHavingNullException, FileUploadException {
        try {
            tran.beginTransaction();
            courseService.modifyCourse(courseId,courseName,courseInfo, author, courseTypeId,
                    courseImageUrl,file,session);
            tran.commit();
        } catch (Exception e){
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

}
