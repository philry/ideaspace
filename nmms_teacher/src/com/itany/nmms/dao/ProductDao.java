package com.itany.nmms.dao;

import java.util.List;

import com.itany.nmms.entity.Product;

/**
 * @author User
 *
 */
public interface ProductDao {
	public List<Product> selectAll();
	
	public Product selectById(Integer id);
	
	public void insert(Product product);
	
	public void updateProduct(Product product);
	
}
