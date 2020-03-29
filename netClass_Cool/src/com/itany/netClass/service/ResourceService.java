package com.itany.netClass.service;

import java.util.List;

import com.itany.netClass.entity.Resource;

public interface ResourceService {

	/**
	 * 查找所有用户资源
	 * */
	public List<Resource> findAll();
	/**
	 * 根据id修改状态
	 * */
	public void modify(Integer id);
}
