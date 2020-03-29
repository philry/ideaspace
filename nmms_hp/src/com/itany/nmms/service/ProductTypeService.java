package com.itany.nmms.service;

import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeNotExistException;

import java.util.List;

public interface ProductTypeService {
    /**
     * 商品类型不能重复，重复的话抛出异常
     * @param productType
     * @throws ProductTypeExistException
     */
    public void insert (ProductType productType) throws ProductTypeExistException;

    /**
     * 查询所有商品类型信息
     * @return
     */
    public List<ProductType> selectAll();

    /**
     * 修改商品类型
     * @param name
     * @throws ParameterException
     * @throws ProductTypeExistException
     */
    public void modify(Integer id,String name)throws ParameterException,ProductTypeExistException;

    /**
     * 通过id查询商品类型信息
     * @param id
     * @return
     * @throws ParameterException
     * @throws ProductTypeNotExistException
     */
    public ProductType findById(Integer id)throws ParameterException,ProductTypeNotExistException;

    /**
     * 修改商品类型状态
     * @param id
     * @throws ParameterException
     */
    public void modifyStatus(Integer id) throws ParameterException;

    /**
     * 查找启用状态的商品类型
     * @return
     * @throws ProductTypeNotExistException
     */
    public List<ProductType> findEnable() throws ProductTypeNotExistException;
}
