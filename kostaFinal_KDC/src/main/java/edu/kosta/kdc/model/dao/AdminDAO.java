package edu.kosta.kdc.model.dao;

public interface AdminDAO {

    /**
     * 방문자 수 저장하는 메소드
     * */
    int userCountIntoDB(int todayUserCount);
    
}
