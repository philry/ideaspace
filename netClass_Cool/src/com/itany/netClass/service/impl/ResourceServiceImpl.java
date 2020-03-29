package com.itany.netClass.service.impl;

import java.util.List;

import com.itany.netClass.dao.ResourceSetMapper;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;


public class ResourceServiceImpl implements ResourceService{
	private ResourceSetMapper resourceSetMapper = ObjectFactory.getObject("resourceMapper");
	/**
	 * 查询所有用户资源的方法
	 * */
	@Override
	public List<Resource> findAll() {
	List<Resource> resources =	resourceSetMapper.findAll();
		return resources;
	}
	/**
	 * 根据id修改状态
	 * */
	@Override
	public void modify(Integer id) {
		Integer status = resourceSetMapper.findById(id);
		if(status==0){
			resourceSetMapper.modify(1, id);
		}
		if(status==1){
			resourceSetMapper.modify(0, id);
		}
		
		
	}

}
