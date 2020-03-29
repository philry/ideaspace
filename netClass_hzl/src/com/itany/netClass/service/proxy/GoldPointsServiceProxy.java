package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointsService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class GoldPointsServiceProxy implements GoldPointsService {
    @Override
    public List<GoldPoints> selectGoldPointsRecordByUserName(String name) {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        GoldPointsService goldPointsService = ObjectFactory.getObject("goldPointsServiceTarget");
        try {
            tran.beginTransaction();
            List<GoldPoints> list = goldPointsService.selectGoldPointsRecordByUserName(name);
            tran.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<Integer> selectMyPointAndGold(String name) {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        GoldPointsService goldPointsService = ObjectFactory.getObject("goldPointsServiceTarget");
        try {
            tran.beginTransaction();
            List<Integer> list = goldPointsService.selectMyPointAndGold(name);
            tran.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }
}
