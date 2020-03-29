package com.itany.netClass.service.proxy;

import java.util.List;

import com.itany.netClass.dao.ResourceSetMapper;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.transaction.TransactionManager;

public class ResourceServiceProxy implements ResourceService{
	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private ResourceService resourceServiceTarget = ObjectFactory.getObject("resourceServiceTarget");
	/**
	 * 查找所有用户资源的事务
	 * */
	@Override
	public List<Resource> findAll() {
		trans.beginTransaction();
		List<Resource> resources = 	resourceServiceTarget.findAll();
		trans.commit();
		return resources;
	}
	/**
	 * 根据id修改状态
	 * */
	@Override
	public void modify(Integer id) {
		trans.beginTransaction();
		resourceServiceTarget.modify(id);
		trans.commit();
	}

}
