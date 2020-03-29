package com.itany.nmms.service.proxy;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.entity.Product;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.tran.TransactionManager;

public class ProductServiceProxy implements ProductService{

	public void addProduct(Product product, CommonsMultipartFile file,
			HttpSession session) throws FileUploadException{
		ProductService productService = (ProductService) ObjectFactory.getObject("productServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		
		tran.beginTransaction();
		try {
			productService.addProduct(product, file, session);
			tran.commit();
		} catch (FileUploadException e) {
			tran.rollback();
			throw e;
		}
	}

	public List<Product> findAll() {
		ProductService productService = (ProductService) ObjectFactory.getObject("productServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		tran.beginTransaction();
		List<Product> products = productService.findAll();
		tran.commit();
		return products;
	}

	public Product findById(String id) throws ParameterException{
		ProductService productService = (ProductService) ObjectFactory.getObject("productServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		tran.beginTransaction();
		try {
			Product product = productService.findById(id);
			tran.commit();
			return product;
		} catch (ParameterException e) {
			tran.rollback();
			throw e;
		}
	}

	public void modifyProduct(Product product, CommonsMultipartFile file,
			HttpSession session) throws FileUploadException {
		ProductService productService = (ProductService) ObjectFactory.getObject("productServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		
		tran.beginTransaction();
		try {
			productService.modifyProduct(product, file, session);
			tran.commit();
		} catch (FileUploadException e) {
			tran.rollback();
			throw e;
		}
	}

}
