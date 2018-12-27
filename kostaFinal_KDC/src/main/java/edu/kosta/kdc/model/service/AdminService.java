package edu.kosta.kdc.model.service;

public interface AdminService {
    
    /**
     * 방문자 수 저장하는 메소드
     * */
    int userCountIntoDB(int todayUserCount);
    
}
