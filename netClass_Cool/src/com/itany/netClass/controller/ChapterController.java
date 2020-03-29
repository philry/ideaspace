package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/chapter")
public class ChapterController {
    private ChapterService chapterService = ObjectFactory.getObject("chapterService");

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(size);
        PageHelper.startPage(pageNo,pageSize);
        List<Chapter> list = chapterService.findAllByCourseId(Integer.parseInt(id));
        PageInfo<Chapter> chapters = new PageInfo<Chapter>(list);
        request.setAttribute("chapters",chapters);
        return "back_courseResourceSet";
    }
}
