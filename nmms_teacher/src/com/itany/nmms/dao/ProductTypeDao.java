package com.itany.nmms.dao;

import java.util.List;

import com.itany.nmms.entity.ProductType;

public interface ProductTypeDao {
	
	/**
	 * 根据名称查询商品类型的信息
	 * @param name
	 * @return
	 */
	public ProductType selectByName(String name);
	
	/**
	 * 添加商品类型的信息
	 * @param productType
	 */
	public void insertType(ProductType productType);

	
	/**
	 * 查询所有商品类型信息
	 * @return
	 */
	public List<ProductType> selectAll();
	
	/**
	 * 根据id查询商品类型信息
	 * @param id
	 * @return
	 */
	public ProductType selectById(Integer id);
	
	/**
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	public ProductType selectByNameAndId(String name,Integer id);
	
	/**
	 * 根据id修改name
	 * @param id
	 * @param name
	 */
	public void updateName(Integer id,String name);
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(Integer id,Integer status);
	
	/**
	 * 根据商品类型的状态查询信息
	 * @param status
	 * @return
	 */
	public List<ProductType> selectByStatus(Integer status);
}
