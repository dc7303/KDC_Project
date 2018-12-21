package edu.kosta.kdc.model.dao;

import edu.kosta.kdc.model.dto.ClassRoomDTO;

public interface ClassRoomDAO {
    
    /**
     * memberId로 디폴트로 등록된 클래스룸 조회하기
     * 
     * @param memberId
     * @return
     */
    ClassRoomDTO currentClassSelectByMemberId(String memberId);
    
    
}
