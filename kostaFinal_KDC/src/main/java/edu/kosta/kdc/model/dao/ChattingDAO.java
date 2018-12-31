package edu.kosta.kdc.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;


public interface ChattingDAO {
    
    ClassRoomInfoDTO infoSelectByMemberId(String memberId);
    
    List<ClassRoomInfoDTO> infoListSelect();
}
