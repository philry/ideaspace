package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.Resource;

public interface ResourceSetMapper {
	
	/**
	 * 查找所有资源的方法
	 * */
	public List<Resource> findAll();
	/**
	 * 根据id查找状态
	 * */
	public Integer findById(Integer id);
	/**
	 * 根据id修改状态
	 * */
	public void modify(Integer status, Integer id);
}
