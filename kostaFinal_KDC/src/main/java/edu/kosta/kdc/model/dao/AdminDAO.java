package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.VisitDTO;

public interface AdminDAO {

    /**
     * 방문자 수 저장하는 메소드
     * */
    int userCountIntoDB(int todayUserCount);

    /**
     * 방문자 수 가져오는 메소드 (최근 5일) 
     * */
    List<VisitDTO> visitNumListSelect();
    
}
