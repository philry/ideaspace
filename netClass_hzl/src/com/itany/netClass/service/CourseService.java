package com.itany.netClass.service;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.CourseExistException;
import com.itany.netClass.exception.CourseNotExistException;
import com.itany.netClass.exception.FileUploadException;


import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface CourseService {
    /**
     * 添加课程
     * @param courseName
     * @param courseInfo
     * @param author
     * @param createDate
     * @param clickNum
     * @param status
     * @param recommendGrade
     * @param courseTypeId
     * @param images
     * @param session
     * @throws FileUploadException
     */
    public void insertCourse(String courseName, String courseInfo, String author,
                             Timestamp timestamp, Integer clickNum, Integer status, String recommendGrade,
                             String courseTypeId, List<CommonsMultipartFile> images, HttpSession session)throws FileUploadException,CourseExistException;

    /**
     * 查询所有课程
     * @return
     */
    public List<Course> findAll();

    /**
     * 在点击页面修改按钮后弹出的修改模态框回显数据
     * @param id
     * @return
     * @throws CourseNotExistException
     */
    public Course findById(Integer id)throws CourseNotExistException;

    /**
     * 在修改模态框修改
     * @param id
     * @param courseName
     * @param courseInfo
     * @param author
     * @param recommendGrade
     * @param courseTypeId
     * @param images
     */
    public void modifyCourse(Integer id,String courseName,String courseInfo,String author,Integer recommendGrade,
                             Integer courseTypeId,List<CommonsMultipartFile> images,HttpSession session) throws FileUploadException;

    public void changeStatus(Integer id);

}
