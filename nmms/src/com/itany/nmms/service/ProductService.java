package com.itany.nmms.service;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.dao.ProductMapper;
import com.itany.nmms.dao.SequenceMapper;
import com.itany.nmms.entity.Sequence;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.factory.ObjectFactory;

import javax.servlet.http.HttpSession;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/2 9:21
 * Description:
 * version:1.0
 */
public interface ProductService {


    public void addProduct(String name, String price, CommonsMultipartFile file, String type, HttpSession session) throws FileUploadException;

}
