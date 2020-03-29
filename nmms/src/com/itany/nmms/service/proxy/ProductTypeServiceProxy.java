package com.itany.nmms.service.proxy;

import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeExistsException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.exception.RequestParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.tran.TransactionManager;

import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/31 15:15
 * Description:
 * version:1.0
 */
public class ProductTypeServiceProxy implements ProductTypeService {
    @Override
    public void addType(String name) throws ProductTypeExistsException {

        ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");

        try {
            tran.begin();
            typeService.addType(name);
            tran.commit();
        } catch (ProductTypeExistsException e) {
            tran.rollback();
            throw e;
        }


    }

    @Override
    public List<ProductType> findAll() {
        ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");

        tran.begin();
        List<ProductType> types = typeService.findAll();
        tran.commit();

        return types;
    }

    @Override
    public ProductType findById(String id) throws RequestParameterException, ProductTypeNotExistException {
        ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");

        try {
            tran.begin();
            ProductType type = typeService.findById(id);
            tran.commit();
            return type;
        } catch (RequestParameterException e) {
            tran.rollback();
            throw e;
        } catch (ProductTypeNotExistException e){
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyName(String id, String name) throws RequestParameterException, ProductTypeExistException {
        ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");

        try {
            tran.begin();
            typeService.modifyName(id,name);
            tran.commit();
        } catch (RequestParameterException e) {
            tran.rollback();
            throw e;
        } catch (ProductTypeExistException e){
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyStatus(String id) throws RequestParameterException {
        ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");

        try {
            tran.begin();
            typeService.modifyStatus(id);
            tran.commit();
        } catch (RequestParameterException e) {
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<ProductType> findEnable() throws ProductTypeNotExistException {

        ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");

        try {
            tran.begin();
            List<ProductType> types = typeService.findEnable();
            tran.commit();
            return types;
        } catch (ProductTypeNotExistException e) {
            tran.rollback();
            throw e;
        }
    }
}
