package com.itany.nmms.service.impl;

import com.itany.nmms.constant.StatusConstant;
import com.itany.nmms.dao.ProductTypeMapper;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.entity.ProductTypeExample;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeExistsException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.exception.RequestParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/31 15:10
 * Description:
 * version:1.0
 */
public class ProductTypeServiceImpl implements ProductTypeService {
    @Override
    public void addType(String name) throws ProductTypeExistsException {

        ProductTypeMapper typeMapper = (ProductTypeMapper) ObjectFactory.getObject("typeMapper");

        ProductTypeExample example = new ProductTypeExample();
        example.or()
                .andNameEqualTo(name);
        List<ProductType> types = typeMapper.selectByExample(example);
        if(!types.isEmpty()){
            throw new ProductTypeExistsException("该商品类型已经存在");
        }
        ProductType type = new ProductType();
        type.setName(name);
        //默认新增的商品类型状态为启用
        type.setStatus(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);

        typeMapper.insertSelective(type);
    }

    @Override
    public List<ProductType> findAll() {
        ProductTypeMapper typeMapper = (ProductTypeMapper) ObjectFactory.getObject("typeMapper");

        ProductTypeExample example = new ProductTypeExample();

        List<ProductType> types = typeMapper.selectByExample(example);

        return types;
    }

    @Override
    public ProductType findById(String id) throws RequestParameterException, ProductTypeNotExistException {
        ProductTypeMapper typeMapper = (ProductTypeMapper) ObjectFactory.getObject("typeMapper");

        if(ParameterUtil.isNull(id)){
            throw new RequestParameterException("id不能为空");
        }

        ProductType type = typeMapper.selectByPrimaryKey(Integer.parseInt(id));
        if(type == null){
            throw new ProductTypeNotExistException("该商品类型不存在");
        }
        return type;
    }

    @Override
    public void modifyName(String id, String name) throws RequestParameterException, ProductTypeExistException {

        ProductTypeMapper typeMapper = (ProductTypeMapper) ObjectFactory.getObject("typeMapper");

        if(ParameterUtil.isNull(id)){
            throw new RequestParameterException("id不能为空");
        }


        ProductTypeExample example = new ProductTypeExample();
        example.or()
                .andIdNotEqualTo(Integer.parseInt(id))
                .andNameEqualTo(name);
        List<ProductType> selectTypes = typeMapper.selectByExample(example);
        if(!selectTypes.isEmpty()){
            throw new ProductTypeExistException("该商品类型已经存在");
        }

        ProductType type = new ProductType();
        type.setId(Integer.parseInt(id));
        type.setName(name);
        typeMapper.updateByPrimaryKeySelective(type);

    }

    @Override
    public void modifyStatus(String id) throws RequestParameterException {

        ProductTypeMapper typeMapper = (ProductTypeMapper) ObjectFactory.getObject("typeMapper");

        if(ParameterUtil.isNull(id)){
            throw new RequestParameterException("id不能为空");
        }

        ProductType type = typeMapper.selectByPrimaryKey(Integer.parseInt(id));

        if(type.getStatus() == StatusConstant.PRODUCT_TYPE_STATUS_ENABLE){
            type.setStatus(StatusConstant.PRODUCT_TYPE_STATUS_DISABLE);
        }else{
            type.setStatus(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);
        }

        typeMapper.updateByPrimaryKeySelective(type);


    }

    @Override
    public List<ProductType> findEnable() throws ProductTypeNotExistException {
        ProductTypeMapper typeMapper = (ProductTypeMapper) ObjectFactory.getObject("typeMapper");

        ProductTypeExample example = new ProductTypeExample();
        example.or()
                .andStatusEqualTo(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);
        List<ProductType> types = typeMapper.selectByExample(example);
        if(types.isEmpty()){
            throw new ProductTypeNotExistException("当前没有有效的商品类型");
        }
        return types;
    }
}
