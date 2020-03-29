package com.itany.nmms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.constant.ProductTypeConstant;
import com.itany.nmms.constant.ResponseCodeConstant;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;
import com.itany.nmms.util.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/type")
public class ProductTypeController {
    ProductTypeService productTypeService= (ProductTypeService) ObjectFactory.getObject("productTypeService");

    @ResponseBody
    @RequestMapping("/addType")
    public ResponseResult insert(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result=new ResponseResult();
        String name=request.getParameter("name");

        ProductType productType=new ProductType();
        productType.setName(name);
        productType.setStatus(ProductTypeConstant.ENABLE.getVal());
        try {
            productTypeService.insert(productType);
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_SUCCESS);
            result.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
       String pageNo=request.getParameter("pageNo");
       String pageSize=request.getParameter("pageSize");
        if(ParameterUtil.isNull(pageNo)) {
           pageNo=DictConstant.BACKEND_PAGE_NO_DEFAULT_VALUE;
        }
        if(ParameterUtil.isNull(pageSize)){
            pageSize = DictConstant.BACKEND_PAGE_SIZE_DEFAULT_VALUE;
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
       try {
            List<ProductType> types=productTypeService.selectAll();
           PageInfo<ProductType> productTypes=new PageInfo<ProductType>(types);
           request.setAttribute("productTypes",productTypes);
           return "backend/productTypeManager";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @ResponseBody
    @RequestMapping("/modifyType")
    public ResponseResult modifyType(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result =new ResponseResult();
        String id=request.getParameter("id");
        String rename =request.getParameter("rename");
        try {
            productTypeService.modify(Integer.parseInt(id),rename);
            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_SUCCESS);
        } catch (ParameterException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
        } catch (ProductTypeExistException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_FAIL);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/findById")
    public ResponseResult findById(HttpServletRequest request, HttpServletResponse response) {
       ResponseResult result=new ResponseResult();
        String id=request.getParameter("id");
        try {
            ProductType productType=productTypeService.findById(Integer.parseInt(id));
            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_SUCCESS);
            result.setReturnObject(productType);
        } catch (ParameterException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
        } catch (ProductTypeNotExistException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_FAIL);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/modifyStatus")
    public ResponseResult modifyStatus(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result=new ResponseResult();
        String id=request.getParameter("id");
        try {
            productTypeService.modifyStatus(Integer.parseInt(id));
            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_SUCCESS);
        } catch (ParameterException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_STATE_FAIL);
        }
        return result;
    }

}
