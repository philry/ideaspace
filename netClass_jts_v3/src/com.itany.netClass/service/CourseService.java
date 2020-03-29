package com.itany.netClass.service;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.exception.ParamsHavingNullException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CourseService {

    public List<Course> selectCoursesOfTopThree();

    public List<Course> selectByParams(Course course);

    public void modifyStatus(Integer id, Integer status);

    public void addCourse(String courseName, String courseInfo, String author,
                          String courseTypeId, CommonsMultipartFile file,
                          HttpSession session) throws FileUploadException, ParamsHavingNullException;

    public void modifyCourse(String courseId, String courseName, String courseInfo,
                             String author, String courseTypeId, String courseImageUrl,
                             CommonsMultipartFile file, HttpSession session) throws ParamsHavingNullException, FileUploadException;
}
