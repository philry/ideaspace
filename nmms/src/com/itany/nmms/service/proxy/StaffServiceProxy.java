package com.itany.nmms.service.proxy;

import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.StaffService;
import com.itany.nmms.tran.TransactionManager;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 16:05
 * Description:
 * version:1.0
 */
public class StaffServiceProxy implements StaffService {
    @Override
    public Staff login(String loginName, String password, String role, String code, String image) throws CodeErrorException, StaffNotExistException {
        StaffService staffService = (StaffService) ObjectFactory.getObject("staffServiceTarget");
        TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
        try {
            tran.begin();;
            Staff staff = staffService.login(loginName,password,role,code,image);
            tran.commit();
            return staff;
        } catch (CodeErrorException e) {
            tran.rollback();
            throw e;
        } catch (StaffNotExistException e) {
            tran.rollback();
            throw e;
        }

    }
}