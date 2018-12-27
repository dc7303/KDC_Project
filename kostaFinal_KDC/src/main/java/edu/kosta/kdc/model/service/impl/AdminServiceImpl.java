package edu.kosta.kdc.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.AdminDAO;
import edu.kosta.kdc.model.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;
    
    /**
     * 방문자 수 저장하는 메소드
     * */
    @Override
    public int userCountIntoDB(int todayUserCount) {
        
        return adminDAO.userCountIntoDB(todayUserCount);
    }

}
