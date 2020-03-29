package com.itany.netClass.service.impl;

import com.itany.netClass.dao.GoldPointMapper;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.exception.PointNotEnoughException;
import com.itany.netClass.exception.PointNotException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointService;
import com.sun.deploy.services.Service;

import java.util.List;



public class GoldPointServiceImpl implements GoldPointService {

    private GoldPointMapper goldPointMapper = ObjectFactory.getObject("goldPointMapper");
    @Override
    public List<GoldPoints> findById(Integer id) {
        List<GoldPoints> goldpoints = goldPointMapper.findById(id);
        return goldpoints;
    }

    @Override
    public Integer selectById(Integer id, Integer point) {
        Integer gold = goldPointMapper.selectById(id, point);
        return gold;
    }

    @Override
    public void addGold(Integer point, Integer id) throws PointNotEnoughException, PointNotException {
        Integer point1 = goldPointMapper.selectById(id, 0);
        if(point1<point){
            throw new PointNotEnoughException("您的积分不足");
        }
        if(point<10){
            throw new PointNotException("积分不能小于10");
        }

        Integer point3 = point*-1;
        goldPointMapper.deletePoint(point3, id);

        Integer point2 = point/10;
        goldPointMapper.addGold(point2, id);
    }
}
