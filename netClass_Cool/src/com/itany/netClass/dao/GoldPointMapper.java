package com.itany.netClass.dao;

import com.itany.netClass.entity.GoldPoints;

import java.util.List;

public interface GoldPointMapper {
    public List<GoldPoints> findById(Integer id);

    public Integer selectById(Integer id , Integer point);

    public void deletePoint (Integer point,Integer id);

    public void addGold(Integer point,Integer id);


}
