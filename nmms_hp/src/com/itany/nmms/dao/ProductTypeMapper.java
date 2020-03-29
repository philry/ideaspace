package com.itany.nmms.dao;

import com.itany.nmms.entity.ProductType;

import java.util.List;

import com.itany.nmms.exception.ProductTypeExistException;
import org.apache.ibatis.annotations.Param;

public interface ProductTypeMapper {
    public void insert(ProductType productType);

    public ProductType selectByName(String name);

    public List<ProductType> findAll();

    public void update(ProductType productType);

    public ProductType selectById(Integer id);
}