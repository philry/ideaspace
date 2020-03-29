package com.itany.nmms.dao;

import com.itany.nmms.entity.Product;

import java.util.List;

import com.itany.nmms.exception.ProductNotExistException;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    /**
     * 插入商品
     * @param product
     */
    public void insertProduct(Product product);

    public List<Product> findAll();
}