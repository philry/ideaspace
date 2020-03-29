package com.itany.nmms.service.proxy;

import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.exception.ProductTypeNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.tran.TransactionManager;

import java.util.List;

public class ProductTypeServiceProxy implements ProductTypeService {
    ProductTypeService productTypeService= (ProductTypeService) ObjectFactory.getObject("productTypeServiceTarget");
    TransactionManager tran= (TransactionManager) ObjectFactory.getObject("tran");
    @Override
    public void insert(ProductType productType) {
        tran.begin();
        try {
            productTypeService.insert(productType);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
        }
    }

    @Override
    public List<ProductType> selectAll() {
        tran.begin();
        try {
            List<ProductType> productTypes= productTypeService.selectAll();
            tran.commit();
            return productTypes;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modify(Integer id, String name) throws ParameterException, ProductTypeExistException {
        tran.begin();
        try {
            productTypeService.modify(id,name);
            tran.commit();
        } catch (ParameterException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        } catch (ProductTypeExistException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }


    @Override
    public ProductType findById(Integer id) throws ParameterException, ProductTypeNotExistException {
        tran.begin();
        try {
            ProductType productType= productTypeService.findById(id);
            tran.commit();
            return productType;
        } catch (ParameterException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        } catch (ProductTypeNotExistException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }

    }

    @Override
    public void modifyStatus(Integer id) throws ParameterException {
        tran.begin();
        try {
            productTypeService.modifyStatus(id);
            tran.commit();
        } catch (ParameterException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<ProductType> findEnable() throws ProductTypeNotExistException {
        tran.begin();
        List<ProductType> types= null;
        try {
            types = productTypeService.findEnable();
            tran.commit();
        } catch (ProductTypeNotExistException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
        return types;
    }
}
