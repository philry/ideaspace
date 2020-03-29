package com.itany.netClass.service;

import com.itany.netClass.entity.Resource;

import java.util.List;

public interface ResourceService {
    public List<Resource> selectAllResource();

    public void changeResourceStatus(Integer id);

    public Resource findById(Integer id);
}
