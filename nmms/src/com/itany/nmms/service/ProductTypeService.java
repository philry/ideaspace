package com.itany.nmms.service;

import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeExistsException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.exception.RequestParameterException;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/31 15:09
 * Description:
 * version:1.0
 */
public interface ProductTypeService {

    /**
     * 添加商品类型的信息
     * 商品类型的名称不可以重复
     * @param name
     */
    public void addType(String name) throws ProductTypeExistsException;

    /**
     * 查询所有商品类型信息
     * @return
     */
    public List<ProductType> findAll();

    /**
     * 根据主键查询商品类型的信息
     * @param id
     * @return
     */
    public ProductType findById(String id) throws RequestParameterException, ProductTypeNotExistException;

    /**
     * 修改商品类型的名称
     * @param id
     * @param name
     */
    public void modifyName(String id,String name) throws RequestParameterException, ProductTypeExistException;

    /**
     * 启用-禁用
     * @param id
     */
    public void modifyStatus(String id) throws RequestParameterException;

    /**
     * 查询有效的商品类型
     * @return
     */
    public List<ProductType> findEnable() throws ProductTypeNotExistException;


}
