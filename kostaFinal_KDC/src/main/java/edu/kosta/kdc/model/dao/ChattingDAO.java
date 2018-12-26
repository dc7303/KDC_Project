package edu.kosta.kdc.model.dao;

import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;


public interface ChattingDAO {
    
    ClassRoomInfoDTO infoSelectByMemberId(String memberId);
}
