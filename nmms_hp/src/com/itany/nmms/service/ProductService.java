package com.itany.nmms.service;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.entity.Product;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProductService {
    /**
     * 添加商品
     * @param name
     * @param price
     * @param file
     * @param id
     * @param session
     * @throws FileUploadException
     */
    public void insert(String name, String price, CommonsMultipartFile file, String id, HttpSession session) throws FileUploadException;

    /**
     * 查询所有商品
     * @return
     */
    public List<Product> findAll();
}


