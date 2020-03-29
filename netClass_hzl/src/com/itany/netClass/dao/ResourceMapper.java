package com.itany.netClass.dao;

import com.itany.netClass.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    public List<Resource> findAllResource();

    public Resource findById(Integer id);

    public void updateResource(Resource resource);
}
