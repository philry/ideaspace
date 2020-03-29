package com.itany.netClass.service;

import java.util.List;

import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.exception.PointNotEnoughException;
import com.itany.netClass.exception.PointNotException;

public interface GoldPointService {

	/**
	 * 根据用户id查找用户积分及金币
	 * */
	public List<GoldPoints> findById(Integer id);
	
	/**
	 * 根据用户id查找金币或者积分
	 * */
	public Integer selectById(Integer id, Integer point);
	/**
	 * 积分兑换金币功能
	 * @throws PointNotEnoughException 
	 * @throws PointNotException 
	 * */
	public void addGold(Integer point, Integer id) throws PointNotEnoughException, PointNotException;
}
