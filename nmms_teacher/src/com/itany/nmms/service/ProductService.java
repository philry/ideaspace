package com.itany.nmms.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.entity.Product;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.exception.ParameterException;

public interface ProductService {
	
	/**
	 * 
	 * @param product
	 * @throws FileUploadException 
	 */
	public void addProduct(Product product,CommonsMultipartFile file,HttpSession session) throws FileUploadException;
	
	/**
	 * 查询所有商品信息
	 * @return
	 */
	public List<Product> findAll();
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 * @throws ParameterException 
	 */
	public Product findById(String id) throws ParameterException;
	
	/**
	 * 修改商品信息
	 * @param product
	 * @param file
	 * @param session
	 * @throws FileUploadException 
	 */
	public void modifyProduct(Product product,CommonsMultipartFile file,HttpSession session) throws FileUploadException;
}
