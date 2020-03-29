package com.itany.nmms.service.proxy;

import java.util.List;

import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.tran.TransactionManager;

public class ProductTypeServiceProxy implements ProductTypeService {

	public void addType(String name) throws ProductTypeExistException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
		
		tran.beginTransaction();
		try {
			typeService.addType(name);
			tran.commit();
		} catch (ProductTypeExistException e) {
			tran.rollback();
			throw e;
		}
		
	}

	public List<ProductType> findAll() {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
		
		tran.beginTransaction();
		List<ProductType> types = typeService.findAll();
		tran.commit();
		
		return types;
	}

	public ProductType findById(String id) throws ParameterException{
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
		
		tran.beginTransaction();
		try {
			ProductType productType = typeService.findById(id);
			tran.commit();
			return productType;
		} catch (ParameterException e) {
			tran.rollback();
			throw e;
		}
	}

	public void modifyType(String id, String name) throws ProductTypeExistException{
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
		tran.beginTransaction();
		try {
			typeService.modifyType(id, name);
			tran.commit();
		} catch (ProductTypeExistException e) {
			tran.rollback();
			throw e;
		}
	}

	public void modifyStatus(String id, String status) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
		tran.beginTransaction();
		typeService.modifyStatus(id, status);
		tran.commit();
	}

	public List<ProductType> findEnable() {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
		tran.beginTransaction();
		List<ProductType> types = typeService.findEnable();
		tran.commit();
		return types;
	}

}
