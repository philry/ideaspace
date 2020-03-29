package com.itany.netClass.service.impl;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseMapper;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.CourseExistsException;
import com.itany.netClass.exception.ParameterException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.util.ParameterUtil;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> findAll() {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        List<Course> courses = courseMapper.findAll();
        return courses;
    }

    @Override
    public void addCourse(String courseName, String courseInfo, String author,
                          String recommendation, String courseTypeId, HttpSession session,
                          List<CommonsMultipartFile> files) throws CourseExistsException, FileUploadException {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        Course course = new Course();
        //根据名字查询是否存在，存在则返回异常
        List<Course> courses = courseMapper.selectByName(courseName);
        if(!courses.isEmpty()){
            throw new CourseExistsException("课程已经存在");
        }
        //课程名
        course.setCourseName(courseName);
        //作者名
        course.setAuthor(author);
        if(!ParameterUtil.isNull(recommendation)){
            //推荐等级
            course.setRecommendation(Integer.parseInt(recommendation));
        }
        //类型id
        course.setCourseTypeId(Integer.parseInt(courseTypeId));

        //获取image的路径
        String path = Constant.FILE_PATH+new SimpleDateFormat("yyyyMMdd").format(new Date());
        String cp = session.getServletContext().getRealPath(path);
        System.out.println(session.getServletContext().getRealPath("")+"_________====");
        //上传的文件只取第一个
        if(!files.isEmpty()){
            File file = new File(cp);
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                files.get(0).transferTo(new File(cp,files.get(0).getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadException("文件上传出错");
            }
            //图片的相对路径
            course.setCoverImage(path+"/"+files.get(0).getOriginalFilename());
        }
        //初始化点击次数
        course.setClickNum(Constant.CLICK_NUMBER_INIT);
        //初始化状态
        course.setStatus(Constant.STATUS_ENABLE);
        System.out.println("run in this pot");
        courseMapper.addCourse(course);
    }

    @Override
    public List<Course> findByData(String author, String courseName, String status,
                                   String courseTypeId, String startDate, String endDate) throws ParameterException {

        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        Course course = new Course();
        try {
            course.setAuthor(author);
            course.setCourseName(courseName);
            course.setStatus(Integer.parseInt(status));
            course.setCourseTypeId(Integer.parseInt(courseTypeId));
            course.setStartDate(startDate);
            course.setEndDate(endDate);
        } catch (NumberFormatException e) {
            throw new ParameterException("参数异常");
        }

        List<Course> courses = courseMapper.selectByData(course);
        return courses;
    }

    @Override
    public void modifyStatusById(String id, String status) throws ParameterException {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        try {
            Course course = new Course();
            course.setId(Integer.parseInt(id));
            course.setStatus(Integer.parseInt(status)==Constant.STATUS_ENABLE?Constant.STATUS_DISABLE:Constant.STATUS_ENABLE);
            courseMapper.modifyStatusById(course);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ParameterException("参数错误");
        }
    }

    @Override
    public void modifyById(Course course,HttpSession session,List<CommonsMultipartFile> files) throws FileUploadException, CourseExistsException {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");

        String path = Constant.FILE_PATH+new SimpleDateFormat("yyyyMMdd").format(new Date());
        String cp = session.getServletContext().getRealPath(path);

        if(!ParameterUtil.isNull(course.getCourseName())){
            if(!courseMapper.selectByName(course.getCourseName()).isEmpty()){
                throw new CourseExistsException("名字已存在");
            }
        }

        if(!files.isEmpty()){
            File file = new File(cp);
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                System.out.println(files.get(0).getOriginalFilename()+"file  name  is ");
                files.get(0).transferTo(new File(cp,files.get(0).getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadException("文件上传出错");
            }
            //图片的相对路径
            course.setCoverImage(path+"/"+files.get(0).getOriginalFilename());
        }
        courseMapper.modifyById(course);
    }
}
