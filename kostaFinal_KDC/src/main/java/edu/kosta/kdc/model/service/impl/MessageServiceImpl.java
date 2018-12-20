package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;
    
    /**
     * 관리자 페이지에서 메시지 확인 할 때 모든 메시지 가져오는 메소드
     * */
    @Override
    public List<MessageDTO> messageSelectAll() {
        
        return messageDAO.messageSelectAll();
        
    }
    
    /**
     * 관리자 페이지에서 쪽지 보내기
     * */
    @Override
    public int insertMessage(MessageDTO messageDTO) {
        
        return messageDAO.insertMessage(messageDTO);
    }

    /**
     * 관리자 페이지에서 쪽지 삭제
     * */
    @Override
    public int deleteMessage(int messageNum) {
        
        return messageDAO.deleteMessage(messageNum);
    
    }

}
