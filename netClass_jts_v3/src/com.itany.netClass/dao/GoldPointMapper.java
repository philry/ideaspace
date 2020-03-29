package com.itany.netClass.dao;

import com.itany.netClass.entity.GoldPoint;

import java.util.List;

public interface GoldPointMapper {
    //签到，添加记录
    public void insert(GoldPoint goldPoint);

    //查询积分
    public List<GoldPoint> selectById(Integer id);
}
