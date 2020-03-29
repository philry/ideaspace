package com.itany.nmms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.constant.ResponseCodeConstant;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeExistsException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.exception.RequestParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;
import com.itany.nmms.util.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/31 15:16
 * Description:
 * version:1.0
 */
@RequestMapping("/type")
public class ProductTypeController {

    private ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeService");

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request, HttpServletResponse response){

        //获取前台传递的分页信息
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");

        //如果前台没有传递分页信息，我们直接使用默认值
        if(ParameterUtil.isNull(pageNo)){
            pageNo = DictConstant.BACKEND_PAGE_NO_DEFAULT_VALUE;
        }
        if(ParameterUtil.isNull(pageSize)){
            pageSize = DictConstant.BACKEND_PAGE_SIZE_DEFAULT_VALUE;
        }

        //使用mybatis分页插件实现分页功能
        //1.设置分页属性，即第几页，一页多少条
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        //2.查询具体的业务数据
        List<ProductType> types = typeService.findAll();
        //3.使用分页插件对于业务数据进行封装，封装成pageInfo对象
        PageInfo<ProductType> pageTypes = new PageInfo<ProductType>(types);

        //将获取到的数据存放到作用域中，方便前台使用
        request.setAttribute("pageTypes",pageTypes);

        return "backend/productTypeManager";
    }

    //@ResponseBody该注解表示当前方法的返回值是一个具体的数据，而不是页面
    @ResponseBody
    @RequestMapping("/addType")
    public ResponseResult addType(HttpServletRequest request, HttpServletResponse response){
        ResponseResult result = new ResponseResult();
        String name = request.getParameter("name");

        try {
            typeService.addType(name);
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
            result.setMessage("成功");
        } catch (ProductTypeExistsException e) {
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public ResponseResult findById(HttpServletRequest request, HttpServletResponse response){
        ResponseResult result = new ResponseResult();

        String id = request.getParameter("id");

        try {
            ProductType type = typeService.findById(id);
            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
            result.setReturnObject(type);
        } catch (RequestParameterException e) {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
        } catch (ProductTypeNotExistException e) {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/modifyName")
    public ResponseResult modifyName(HttpServletRequest request, HttpServletResponse response){
        ResponseResult result = new ResponseResult();

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        try {
            typeService.modifyName(id,name);
            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
        } catch (RequestParameterException e) {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
        } catch (ProductTypeExistException e) {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }


        return result;
    }

    @ResponseBody
    @RequestMapping("/modifyStatus")
    public ResponseResult modifyStatus(HttpServletRequest request, HttpServletResponse response){
        ResponseResult result = new ResponseResult();


        String id = request.getParameter("id");

        try {
            typeService.modifyStatus(id);
            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
        } catch (RequestParameterException e) {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
        }

        return result;
    }

}
