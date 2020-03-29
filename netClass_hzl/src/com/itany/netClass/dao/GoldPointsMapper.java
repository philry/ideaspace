package com.itany.netClass.dao;

import com.itany.netClass.entity.GoldPoints;

import java.util.List;

public interface GoldPointsMapper {

    public void insert(GoldPoints gp);

    public List<GoldPoints> selectGoldPointsRecordByUserId(Integer id);

    public Integer selectMyPointByUserId(Integer id);

    public Integer selectMyGoldByUserId(Integer id);
}
