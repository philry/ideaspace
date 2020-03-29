package com.itany.netClass.service;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.CourseExistsException;
import com.itany.netClass.exception.ParameterException;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CourseService {
    /**
     * 查询所有课程
     * @return
     */
    public List<Course> findAll();

    /**
     * 添加课程
     * @param courseName
     * @param courseInfo
     * @param author
     * @param recommendation
     * @param courseTypeId
     * @param session
     * @param files
     */
    public void addCourse(String courseName, String courseInfo, String author, String recommendation,
                          String courseTypeId, HttpSession session, List<CommonsMultipartFile> files) throws CourseExistsException, FileUploadException;

    /**
     * 根据传入的参数查询课程
     * @param author
     * @param courseName
     * @param status
     * @param courseTypeId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Course> findByData(String author, String courseName, String status,
                                   String courseTypeId, String startDate, String endDate) throws ParameterException;


    /**
     * 根据id修改status
     * @param id
     * @param status
     */
    public void modifyStatusById(String id, String status) throws ParameterException;

    /**
     * 根据id修改数据
     * @param course
     */
    public void modifyById(Course course, HttpSession session, List<CommonsMultipartFile> files) throws FileUploadException, CourseExistsException;



}
