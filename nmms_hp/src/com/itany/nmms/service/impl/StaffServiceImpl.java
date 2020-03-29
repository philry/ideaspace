package com.itany.nmms.service.impl;

import com.itany.nmms.constant.StatusConstant;
import com.itany.nmms.dao.StaffMapper;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.entity.StaffExample;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.StaffService;
import com.itany.nmms.util.MD5Util;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    @Override
    public Staff login(String loginName, String password, String role, String code, String image) throws CodeErrorException, StaffNotExistException {
        StaffMapper staffMapper= (StaffMapper) ObjectFactory.getObject("staffMapper");
        if(!code.equals(image)){
            throw new CodeErrorException("验证码不正确");
        }
        StaffExample example=new StaffExample();
        example.or().andLoginNameEqualTo(loginName)
                .andPasswordEqualTo(MD5Util.md5(password))
                .andRoleEqualTo(role)
                .andIsValidEqualTo(StatusConstant.STAFF_IS_VALID_ENABLE);
        List<Staff> staffs=staffMapper.selectByExample(example);
        if(staffs.isEmpty()){
            throw new StaffNotExistException("帐号或密码错误");
        }
        return staffs.get(0);
    }
}
