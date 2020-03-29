package com.itany.nmms.service;

import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffNotExistException;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 15:56
 * Description:
 * version:1.0
 */
public interface StaffService {

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @param role
     * @param code
     * @param image
     * @return
     */
    public Staff login(String loginName,String password,String role,String code,String image) throws CodeErrorException, StaffNotExistException;

}
