package com.itany.netClass.service.impl;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseMapper;
import com.itany.netClass.dao.CourseTypeMapper;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseExistException;
import com.itany.netClass.exception.CourseNotExistException;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    @Override
    public void insertCourse(String courseName, String courseInfo, String author,
                             Timestamp timestamp, Integer clickNum, Integer status, String recommendGrade,
                             String courseTypeId, List<CommonsMultipartFile> images, HttpSession session) throws FileUploadException, CourseExistException {

        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");

        Course course1=courseMapper.findByName(courseName);
        if(course1!=null){
            throw new CourseExistException("课程已存在");
        }

        Course course=new Course();
        course.setName(courseName);
        course.setCourseInfo(courseInfo);
        course.setAuthor(author);
        course.setCreateDate(timestamp);
        course.setClickNum(clickNum);
        course.setStatus(status);
        course.setRecommmendGreade(Integer.parseInt(recommendGrade));

        CourseType type=courseTypeMapper.findById(Integer.parseInt(courseTypeId));
        course.setCourseType(type);

        //创建目录，上传文件，并将文件信息交给course，cp是服务器的绝对路径
        String path = "/"+Constant.UPLOAD_PATH_PREFIX +"/"+new SimpleDateFormat("yyyyMMdd").format(new Date());
        String cp=session.getServletContext().getRealPath(path);
//        String cp="E:\\ideaspace\\netClass_hzl\\web\\images";
        //创建该目录
        //要求，如果有，不创建，如果没有，创建
        File f=new File(cp);
        f.mkdirs();
        for(CommonsMultipartFile image:images){
        //    System.out.println("name="+image.getOriginalFilename()+",isEmpty="+image.isEmpty());
            course.setImageUrl(path+"/"+image.getOriginalFilename());
            courseMapper.insertCourse(course);
            try {
                image.transferTo(new File(cp,image.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadException("图片上传出错");
            }
        }
    }

    @Override
    public List<Course> findAll() {
        CourseMapper courseMapper=ObjectFactory.getObject("courseMapper");
        List<Course> courses=courseMapper.findAll();
        return courses;
    }

    @Override
    public Course findById(Integer id) throws CourseNotExistException {
        CourseMapper courseMapper=ObjectFactory.getObject("courseMapper");
        Course course=courseMapper.findById(id);
        if (course == null){
            throw new CourseNotExistException("课程不存在");
        }
        return course;
    }

    @Override
    public void modifyCourse(Integer id,String courseName,String courseInfo,String author,Integer recommendGrade,
                             Integer courseTypeId,List<CommonsMultipartFile> images,HttpSession session)throws FileUploadException{
        CourseMapper courseMapper=ObjectFactory.getObject("courseMapper");
        CourseTypeMapper courseTypeMapper=ObjectFactory.getObject("courseTypeMapper");

        Course course =courseMapper.findById(id);
        CourseType courseType=courseTypeMapper.findById(courseTypeId);

        course.setName(courseName);
        course.setCourseInfo(courseInfo);
        course.setAuthor(author);
        course.setRecommmendGreade(recommendGrade);
        course.setCourseType(courseType);

        //创建目录，上传文件，并将文件信息交给course，cp是服务器的绝对路径
        String path = "/"+Constant.UPLOAD_PATH_PREFIX +"/"+ new SimpleDateFormat("yyyyMMdd").format(new Date());
        String cp=session.getServletContext().getRealPath(path);
        //创建该目录
        //要求，如果有，不创建，如果没有，创建
        File f=new File(cp);
        f.mkdirs();
        for(CommonsMultipartFile image:images){
            course.setImageUrl(path+"/"+image.getOriginalFilename());
            courseMapper.updateCourse(course);
            try {
                image.transferTo(new File(cp,image.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadException("图片上传出错");
            }
        }
    }

    @Override
    public void changeStatus(Integer id) {
        CourseMapper courseMapper=ObjectFactory.getObject("courseMapper");
        Course course =courseMapper.findById(id);
        if(course.getStatus()==Constant.STATUS_ENABLE){
            course.setStatus(Constant.STATUS_DISABLE);
        }else{
            course.setStatus(Constant.STATUS_ENABLE);
        }
        courseMapper.updateCourse(course);
    }
}
