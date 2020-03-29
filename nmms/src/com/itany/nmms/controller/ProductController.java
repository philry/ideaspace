package com.itany.nmms.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.service.ProductTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/2 9:03
 * Description:
 * version:1.0
 */
@RequestMapping("/product")
public class ProductController {

    private ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeService");
    private ProductService productService = (ProductService) ObjectFactory.getObject("productService");

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request, HttpServletResponse response){
        try {
            List<ProductType> types = typeService.findEnable();
            request.setAttribute("types",types);
        } catch (ProductTypeNotExistException e) {
            e.printStackTrace();
        }
        return "backend/productManager";
    }

    @RequestMapping("/addProduct")
    public String addProduct(HttpServletRequest request, HttpServletResponse response, CommonsMultipartFile file){
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String type = request.getParameter("type");

        try {
            productService.addProduct(name,price,file,type,request.getSession());
        } catch (FileUploadException e) {
            request.getSession().setAttribute("productMsg",e.getMessage());
        }
        return "redirect:/product/findAll.do";
    }

}
