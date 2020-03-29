package com.itany.netClass.service.impl;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.dao.CourseMapper;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.exception.ParamsHavingNullException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.util.CommonUtil;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> selectCoursesOfTopThree() {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        List<Course> courses = courseMapper.selectCoursesOfTopThree();
        return courses;
    }

    @Override
    public List<Course> selectByParams(Course course) {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        List<Course> courses = courseMapper.selectByParams(course);
        return courses;
    }

    @Override
    public void modifyStatus(Integer id, Integer status) {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        if(status==0){
            status=1;
        }else{
            status=0;
        }
        courseMapper.modifyStatus(id,status);
    }

    @Override
    public void addCourse(String courseName, String courseInfo, String author, String courseTypeId, CommonsMultipartFile file, HttpSession session) throws FileUploadException, ParamsHavingNullException {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        //添加课程时各属性：
        //courseName,courseInfo,author,给出
        //courseType的id给出
        //createDate是当前时间
        //clickNumber默认为0
        //status默认是0
        //recommendation_grade为null
        //coverImageUrl通过文件上传
        if(CommonUtil.isNull(courseName) || CommonUtil.isNull(courseInfo) ||
                CommonUtil.isNull(author) || CommonUtil.isNull(courseTypeId)
                || file==null){
            throw new ParamsHavingNullException("不可有空值");
        }
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseInfo(courseInfo);
        course.setAuthor(author);
        CourseType t = new CourseType();
        t.setId(Integer.parseInt(courseTypeId));
        course.setCourseType(t);
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp timestamp=null;
        try {
            timestamp = new Timestamp(format.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        course.setCreateDate(timestamp);
        course.setClickNumber(0);
        course.setStatus(0);

        String path = "/backCourseAdd/"+new SimpleDateFormat("yyMMdd").format(new Date());
        String cp = session.getServletContext().getRealPath(path);
        File f = new File(cp);
        f.mkdirs();
        course.setCoverImageUrl(path+"/"+file.getOriginalFilename());
        courseMapper.addCourse(course);

        try {
            file.transferTo(new File(cp,file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileUploadException("文件上传出错,请检查是否选择图片");
        }

    }

    @Override
    public void modifyCourse(String courseId, String courseName, String courseInfo, String author, String courseTypeId, String courseImageUrl, CommonsMultipartFile file, HttpSession session) throws ParamsHavingNullException, FileUploadException {
        CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
        if(CommonUtil.isNull(courseName) || CommonUtil.isNull(courseInfo) ||
                CommonUtil.isNull(author) || CommonUtil.isNull(courseTypeId)
                ){
            throw new ParamsHavingNullException("不可有空值");
        }
        Course courseParams1 = new Course();
        Course courseParams2 = new Course();
        courseParams1.setId(Integer.parseInt(courseId));
        courseParams2.setId(Integer.parseInt(courseId));
        List<Course> courses = courseMapper.selectByParams(courseParams1);
        Course course = courses.get(0);
        courseParams2.setCourseName(courseName);
        courseParams2.setCourseInfo(courseInfo);
        courseParams2.setAuthor(author);
        CourseType t = new CourseType();t.setId(Integer.parseInt(courseTypeId));
        courseParams2.setCourseType(t);
        if(file.isEmpty()){
            courseParams2.setCoverImageUrl(null);
        }
        else{
            String path = "/backCourseAdd/"+new SimpleDateFormat("yyMMdd").format(new Date());
            String cp = session.getServletContext().getRealPath(path);
            File f = new File(cp);
            f.mkdirs();
            courseParams2.setCoverImageUrl(path+"/"+file.getOriginalFilename());

            try {
                file.transferTo(new File(cp,file.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadException("文件上传出错");
            }
        }
        courseMapper.modifyCourse(courseParams2);
    }

}
