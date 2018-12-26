package edu.kosta.kdc.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.ChattingDAO;
import edu.kosta.kdc.model.dao.impl.ChattingDAOImpl;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.service.ChattingService;
import edu.kosta.kdc.util.KdcException;

@Service
public class ChattingServiceImpl implements ChattingService {

    @Autowired
    private ChattingDAO dao;
    
    @Override
    public ClassRoomInfoDTO infoSelectByMemberId(String memberId) {
        ClassRoomInfoDTO result = dao.infoSelectByMemberId(memberId);
        if(result==null) throw new KdcException("클래스 코드가 등록되어있지 않습니다.");
        return result;
    }

}
