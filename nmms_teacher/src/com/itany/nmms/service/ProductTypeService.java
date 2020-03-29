package com.itany.nmms.service;

import java.util.List;

import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;

public interface ProductTypeService {

	
	/**
	 * 添加商品类型信息
	 * 商品类型的名称不能重复
	 * @param name
	 * @throws ProductTypeExistException 
	 */
	public void addType(String name) throws ProductTypeExistException;
	
	/**
	 * 查询所有商品类型信息
	 * @return
	 */
	public List<ProductType> findAll();
	
	/**
	 * 根据id查询商品类型信息
	 * @param id
	 * @return
	 * @throws ParameterException 
	 */
	public ProductType findById(String id) throws ParameterException;
	
	/**
	 * ·修改商品类型信息
	 * 商品类型名称不能重复
	 * 商品类型的名称可以是原来的
	 * @throws ProductTypeExistException 
	 */
	public void modifyType(String id,String name) throws ProductTypeExistException;
	
	/**
	 * 
	 * @param id
	 * @param status
	 */
	public void modifyStatus(String id,String status);
	
	/**
	 * 查询所有的有效的商品类型
	 * @return
	 */
	public List<ProductType> findEnable();
}
