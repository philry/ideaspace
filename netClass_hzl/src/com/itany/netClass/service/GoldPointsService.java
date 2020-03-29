package com.itany.netClass.service;

import com.itany.netClass.entity.GoldPoints;

import java.util.List;

public interface GoldPointsService {
    public List<GoldPoints> selectGoldPointsRecordByUserName(String name);

    public List<Integer> selectMyPointAndGold(String name);
}
