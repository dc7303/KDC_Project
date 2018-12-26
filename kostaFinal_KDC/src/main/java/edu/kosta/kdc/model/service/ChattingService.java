package edu.kosta.kdc.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.dto.MemberDTO;


public interface ChattingService {
    
    
    ClassRoomInfoDTO infoSelectByMemberId(String memberId);

}
