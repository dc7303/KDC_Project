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
    
    /**
     * member가 클래스 코드 입력하면 DB에 해당 유저와 코드를 입력시켜주는 메소드
     * */
    int insertMemberIntoClass(String memberClassCode);
    
}
