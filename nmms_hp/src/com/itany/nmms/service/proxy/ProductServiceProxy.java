package com.itany.nmms.service.proxy;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.entity.Product;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.tran.TransactionManager;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ProductServiceProxy implements ProductService {


    @Override
    public void insert(String name, String price, CommonsMultipartFile file, String id, HttpSession session) throws FileUploadException {
        ProductService productService= (ProductService) ObjectFactory.getObject("productServiceTarget");
        TransactionManager tran= (TransactionManager) ObjectFactory.getObject("tran");
        tran.begin();
        try {
            productService.insert(name,price,file,id,session);
            tran.commit();
        } catch (FileUploadException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<Product> findAll() {
        ProductService productService= (ProductService) ObjectFactory.getObject("productServiceTarget");
        TransactionManager tran= (TransactionManager) ObjectFactory.getObject("tran");
        tran.begin();
        List<Product> products=productService.findAll();
        tran.commit();
        return products;
    }
}
