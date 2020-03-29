package com.itany.netClass.service.proxy;

import com.itany.netClass.dao.GoldPointMapper;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.exception.PointNotEnoughException;
import com.itany.netClass.exception.PointNotException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class GoldPointServiceProxy implements GoldPointService {

    private TransactionManager trans = ObjectFactory.getObject("transaction");
    private GoldPointService goldPointServiceTarget = ObjectFactory.getObject("goldPointServiceTarget");

    @Override
    public List<GoldPoints> findById(Integer id) {
        trans.beginTransaction();
        List<GoldPoints> goldPoints = goldPointServiceTarget.findById(id);
        trans.commit();
        return goldPoints;
    }

    @Override
    public Integer selectById(Integer id, Integer point) {
        trans.beginTransaction();
        Integer gold = goldPointServiceTarget.selectById(id, point);
        trans.commit();
        return gold;
    }

    @Override
    public void addGold(Integer point, Integer id) throws PointNotEnoughException, PointNotException {
        try {
            trans.beginTransaction();
            goldPointServiceTarget.addGold(point, id);
            trans.commit();
        } catch (PointNotEnoughException e) {
            trans.rollback();
            throw e;
        } catch (PointNotException e) {
            trans.rollback();
            throw e;
        }
    }

}
