package com.itany.nmms.service.proxy;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.tran.TransactionManager;

import javax.servlet.http.HttpSession;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/2 10:05
 * Description:
 * version:1.0
 */
public class ProductServiceProxy implements ProductService {
    @Override
    public void addProduct(String name, String price, CommonsMultipartFile file, String type, HttpSession session) throws FileUploadException {
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
        ProductService productService = (ProductService) ObjectFactory.getObject("productServiceTarget");
        try {
            tran.begin();
            productService.addProduct(name,price,file,type,session);
            tran.commit();
        } catch (FileUploadException e) {
            tran.rollback();
            throw e;
        }
    }
}
