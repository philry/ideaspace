package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.exception.PointNotEnoughException;
import com.itany.netClass.exception.PointNotException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/goldPoint")
public class GoldPointController {

    private GoldPointService goldPointService = ObjectFactory.getObject("goldPointService");

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request,
                          HttpServletResponse response) {
        String id = request.getParameter("id");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        pageSize = "3";
        if (ParameterUtil.isNull(pageNo)) {
            pageNo = Constant.BACKEND_PAGE_NO_DEFAULT;
        }
        if (ParameterUtil.isNull(pageSize)) {
            pageSize = Constant.BACKEND_PAGE_SIZE_DEFAULT;
        }
        PageHelper.startPage(Integer.parseInt(pageNo),
                Integer.parseInt(pageSize));

        List<GoldPoints> goldPoints = goldPointService.findById(Integer.parseInt(id));

        PageInfo<GoldPoints> pageGoldPoints = new PageInfo<GoldPoints>(goldPoints);
        request.setAttribute("pageGoldPoints",pageGoldPoints);

        return "front/front_record";
    }


    @RequestMapping("/change")
    public AjaxResult change(HttpServletRequest request,
                             HttpServletResponse response) {
        AjaxResult ar = new AjaxResult();
        String point = request.getParameter("point");
        String id = request.getParameter("id");

        try {
            goldPointService.addGold(Integer.parseInt(point), Integer.parseInt(id));
            ar.setSuccess(true);
            ar.setMsg("兑换成功");
            return ar;
        } catch (PointNotEnoughException e) {
            ar.setSuccess(false);
            ar.setMsg("兑换失败");
            return ar;
        } catch (PointNotException e) {
            ar.setSuccess(false);
            ar.setMsg("兑换失败");
            return ar;
        }
    }
}
