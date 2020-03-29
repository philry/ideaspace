package com.itany.netClass.service.impl;

import com.itany.netClass.dao.GoldPointsMapper;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointsService;

import java.util.ArrayList;
import java.util.List;

public class GoldPointsServiceImpl implements GoldPointsService {
    @Override
    public List<GoldPoints> selectGoldPointsRecordByUserName(String name) {
        UserMapper userMapper = ObjectFactory.getObject("userMapper");
        GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");
        User user = userMapper.selectByLoginName(name);
        List<GoldPoints> list = goldPointsMapper.selectGoldPointsRecordByUserId(user.getId());
        return list;
    }

    @Override
    public List<Integer> selectMyPointAndGold(String name) {
        UserMapper userMapper = ObjectFactory.getObject("userMapper");
        GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");

        User user = userMapper.selectByLoginName(name);
        Integer point = goldPointsMapper.selectMyPointByUserId(user.getId());
        Integer gold = goldPointsMapper.selectMyGoldByUserId(user.getId());
        List<Integer> list = new ArrayList<>();
        list.add(point);
        list.add(gold);
        return list;
    }
}
