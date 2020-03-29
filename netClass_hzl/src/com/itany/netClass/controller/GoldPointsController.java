package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointsService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/record")
public class GoldPointsController {
    @RequestMapping("/findPointRecord")
    public String findPointRecord(HttpServletRequest request, HttpServletResponse response) {
        GoldPointsService goldPointsService = ObjectFactory.getObject("goldPointsService");
        String name = request.getParameter("loginName");
        String nickname = request.getParameter("nickname");
        System.out.println(name);
        String gold = request.getParameter("gold");
        String point = request.getParameter("point");

        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if (ParameterUtil.isNull(pageNo)) {
            pageNo = "1";
        }
        if (ParameterUtil.isNull(pageSize)) {
            pageSize = "5";
        }
        try {
            PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
            List<GoldPoints> list = goldPointsService.selectGoldPointsRecordByUserName(name);
            PageInfo<GoldPoints> goldPointsRecords = new PageInfo<GoldPoints>(list);
            request.setAttribute("goldPointsRecords", goldPointsRecords);
            request.setAttribute("gold", gold);
            request.setAttribute("point", point);
            request.setAttribute("nickname", nickname);
            return "front_record";
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return "front_record";
    }

    @ResponseBody
    @RequestMapping("/findMyPoint")
    public AjaxResult findMePointAndGold(HttpServletRequest request, HttpServletResponse response) {
        GoldPointsService goldPointsService = ObjectFactory.getObject("goldPointsService");
        AjaxResult ar = new AjaxResult();
        String name = request.getParameter("loginName");
        try {
            List<Integer> list = goldPointsService.selectMyPointAndGold(name);
            System.out.println(list.get(0));
            System.out.println(list.get(1));
            ar.setSuccess(true);
            ar.setObj(list);
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }

        return ar;
    }
}
