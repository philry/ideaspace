package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class ResourceServiceProxy implements ResourceService {
    @Override
    public List<Resource> selectAllResource() {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        ResourceService resourceService = ObjectFactory.getObject("resourceServiceTarget");
        try {
            tran.beginTransaction();
            List<Resource> list = resourceService.selectAllResource();
            tran.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void changeResourceStatus(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        ResourceService resourceService = ObjectFactory.getObject("resourceServiceTarget");
        try {
            tran.beginTransaction();
            resourceService.changeResourceStatus(id);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public Resource findById(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        ResourceService resourceService = ObjectFactory.getObject("resourceServiceTarget");
        try {
            tran.beginTransaction();
            Resource resource = resourceService.findById(id);
            tran.commit();
            return resource;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }
}
