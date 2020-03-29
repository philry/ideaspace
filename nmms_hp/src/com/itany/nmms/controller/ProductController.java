package com.itany.nmms.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.entity.Product;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.service.ProductTypeService;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/product")
public class ProductController {
    ProductService productService= (ProductService) ObjectFactory.getObject("productService");
    ProductTypeService productTypeService= (ProductTypeService) ObjectFactory.getObject("productTypeService");

   @RequestMapping("/addProduct")
    public String insert(HttpServletRequest request, HttpServletResponse response,CommonsMultipartFile file) {
        Product product=new Product();
        String name=request.getParameter("productName");
        String price=request.getParameter("price");
        String typeId=request.getParameter("typeId");
        try {
           productService.insert(name,price,file,typeId,request.getSession());
        } catch (FileUploadException e) {
            e.printStackTrace();
            request.getSession().setAttribute("productMsg",e.getMessage());
        }
        return "redirect:/product/findAll.do"; //SpringMvc封装的转发格式
    }


    @RequestMapping("/findAll")
    public String fiandAll(HttpServletRequest request,HttpServletResponse response){
        try {
            List<ProductType> productTypes=productTypeService.findEnable();
            List<Product> products=productService.findAll();
            request.setAttribute("productTypes",productTypes);
            request.setAttribute("products",products);
        } catch (ProductTypeNotExistException e) {
            e.printStackTrace();
        }
        return "backend/productManager";
    }
}
