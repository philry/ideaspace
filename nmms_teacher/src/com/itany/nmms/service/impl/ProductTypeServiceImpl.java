package com.itany.nmms.service.impl;

import java.util.List;

import com.itany.nmms.constant.StatusConstant;
import com.itany.nmms.dao.ProductTypeDao;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;

public class ProductTypeServiceImpl implements ProductTypeService{
	

	public void addType(String name) throws ProductTypeExistException {
		ProductTypeDao typeDao = (ProductTypeDao) ObjectFactory.getObject("typeDao");
		
		//根据名称判断是否已经存在
		ProductType selectType = typeDao.selectByName(name);
		//若存在，抛出异常
		if(selectType != null){
			throw new ProductTypeExistException("该商品类型已经存在");
		}
		
		//若不存在，执行添加操作
		//且我们设计状态默认为启用
		ProductType productType = new ProductType();
		productType.setName(name);
		productType.setStatus(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);
		typeDao.insertType(productType);
		
	}

	public List<ProductType> findAll() {
		ProductTypeDao typeDao = (ProductTypeDao) ObjectFactory.getObject("typeDao");
		return typeDao.selectAll();
	}

	public ProductType findById(String id) throws ParameterException {
		ProductTypeDao typeDao = (ProductTypeDao) ObjectFactory.getObject("typeDao");
		if(ParameterUtil.isNull(id)){
			throw new ParameterException("商品类型id不能为空");
		}
		
		try {
			ProductType productType = typeDao.selectById(Integer.parseInt(id));
			return productType;
		} catch (NumberFormatException e) {
			throw new ParameterException("商品类型id只能是一个数字");
		}
		
	}

	public void modifyType(String id,String name) throws ProductTypeExistException {
		ProductTypeDao typeDao = (ProductTypeDao) ObjectFactory.getObject("typeDao");
		ProductType selectType = typeDao.selectByNameAndId(name, Integer.parseInt(id));
		if(selectType != null){
			throw new ProductTypeExistException("该商品类型已经存在");
		}
		
		typeDao.updateName(Integer.parseInt(id), name);
	}

	public void modifyStatus(String id, String status) {
		ProductTypeDao typeDao = (ProductTypeDao) ObjectFactory.getObject("typeDao");
		int typeStatus = Integer.parseInt(status);
		if(typeStatus == StatusConstant.PRODUCT_TYPE_STATUS_DISABLE){
			typeStatus = StatusConstant.PRODUCT_TYPE_STATUS_ENABLE;
		}else{
			typeStatus = StatusConstant.PRODUCT_TYPE_STATUS_DISABLE;
		}
		typeDao.updateStatus(Integer.parseInt(id), typeStatus);
	}

	public List<ProductType> findEnable() {
		ProductTypeDao typeDao = (ProductTypeDao) ObjectFactory.getObject("typeDao");
		List<ProductType> types = typeDao.selectByStatus(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE); 
		return types;
	}

}
