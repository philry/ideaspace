package com.itany.nmms.service.impl;

import com.itany.nmms.constant.ProductTypeConstant;
import com.itany.nmms.constant.StatusConstant;
import com.itany.nmms.dao.ProductTypeMapper;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService {

    @Override
    public void insert(ProductType productType) throws ProductTypeExistException {
        ProductTypeMapper productTypeMapper= (ProductTypeMapper) ObjectFactory.getObject("productTypeMapper");
        ProductType type1=productTypeMapper.selectByName(productType.getName());
        if(type1==productType){
            throw new ProductTypeExistException("商品类型已存在");
        }
        productTypeMapper.insert(productType);
    }

    @Override
    public List<ProductType> selectAll() {
        ProductTypeMapper productTypeMapper= (ProductTypeMapper) ObjectFactory.getObject("productTypeMapper");
        List<ProductType> productTypes=productTypeMapper.findAll();
        return productTypes.isEmpty()?null:productTypes;
    }

    @Override
    public void modify(Integer id,String name) throws ParameterException, ProductTypeExistException {
        ProductTypeMapper productTypeMapper= (ProductTypeMapper) ObjectFactory.getObject("productTypeMapper");
        if(ParameterUtil.isNull(name)){
            throw new ParameterException("name不能为空");
        }
        ProductType selectedType=productTypeMapper.selectById(id);
        if(name==selectedType.getName()){
            throw new ProductTypeExistException("该商品类型已存在");
        }

        ProductType productType=new ProductType();
        productType.setId(id);
        productType.setName(name);
        productType.setStatus(ProductTypeConstant.ENABLE.getVal());
        productTypeMapper.update(productType);
    }

    @Override
    public ProductType findById(Integer id) throws ParameterException, ProductTypeNotExistException {
        ProductTypeMapper productTypeMapper= (ProductTypeMapper) ObjectFactory.getObject("productTypeMapper");
        if(ParameterUtil.isNull(id)){
            throw new ParameterException("id不能为空");
        }

        ProductType productType=productTypeMapper.selectById(id);
        if(productType==null){
            throw new ProductTypeNotExistException("商品类型不存在");
        }
        return productType;
    }

    @Override
    public void modifyStatus(Integer id) throws ParameterException {
        ProductTypeMapper productTypeMapper= (ProductTypeMapper) ObjectFactory.getObject("productTypeMapper");
        if(ParameterUtil.isNull(id)){
            throw new ParameterException("id不能为空");
        }
        ProductType type=productTypeMapper.selectById(id);
        if(type.getStatus()==0){
            type.setStatus(ProductTypeConstant.ENABLE.getVal());
        }else{
            type.setStatus(ProductTypeConstant.DISABLE.getVal());
        }
        productTypeMapper.update(type);
    }

    @Override
    public List<ProductType> findEnable() throws ProductTypeNotExistException {
        ProductTypeMapper productTypeMapper= (ProductTypeMapper) ObjectFactory.getObject("productTypeMapper");
        List<ProductType> types=new ArrayList<>();
        List<ProductType> productTypes=productTypeMapper.findAll();
        for(ProductType type:productTypes){
            if(type.getStatus()==StatusConstant.PRODUCT_TYPE_ENABLE){
                types.add(type);
            }
        }
        if(types.isEmpty()){
            throw new ProductTypeNotExistException("当前没有有效的商品类型");
        }
        return types;
    }


}
