package com.itany.nmms.dao;

import com.itany.nmms.entity.Sequence;

public interface SequenceDao {

	/**
	 * 根据name查询对应的序列号
	 * @param name	对应的编号前缀
	 * @return
	 */
	public Sequence selectByName(String name);
	
	/**
	 * 添加sequence信息
	 * @param sequence
	 */
	public void insertSequence(Sequence sequence);
	
	/**
	 * 根据name修改value值
	 * @param name
	 * @param value
	 */
	public void updateValueByName(String name,String value);
}
