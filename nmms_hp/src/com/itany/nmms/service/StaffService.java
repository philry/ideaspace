package com.itany.nmms.service;

import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffNotExistException;

public interface StaffService {
    /**
     *
     * @param loginName
     * @param password
     * @param role
     * @param code
     * @param image
     * @return
     * @throws CodeErrorException
     * @throws StaffNotExistException
     */
    public Staff login(String loginName,String password,String role,String code,String image)throws CodeErrorException, StaffNotExistException;
}
