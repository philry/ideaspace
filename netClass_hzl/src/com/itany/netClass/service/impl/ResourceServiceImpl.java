package com.itany.netClass.service.impl;

import com.itany.netClass.dao.ResourceMapper;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;

import java.util.List;

public class ResourceServiceImpl implements ResourceService {
    @Override
    public List<Resource> selectAllResource() {
        ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");
        List<Resource> list = resourceMapper.findAllResource();
        return list;
    }

    @Override
    public void changeResourceStatus(Integer id) {
        ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");
        Resource resource = resourceMapper.findById(id);
        if (resource.getStatus() == 0) {
            resource.setStatus(1);
        } else {
            resource.setStatus(0);
        }
        resourceMapper.updateResource(resource);
    }

    @Override
    public Resource findById(Integer id) {
        ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");
        Resource resource = resourceMapper.findById(id);
        return resource;
    }
}
