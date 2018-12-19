package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.util.KdcException;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;
    
    @Override
    public List<MessageDTO> messageAll(String id){
        
        return messageDAO.messageAll(id);
        
    }

    @Override
    public void messageInsert(MessageDTO messageDTO){

            messageDAO.messageInsert(messageDTO);

        
    }

    @Override
    public void messageDelete(int messageNum) {
        
        messageDAO.messageDelete(messageNum);

    }


    @Override
    @Transactional
    public MessageDTO selectByMesssage(int messageNum) {
        
        messageDAO.isReadMessage(messageNum);
        
        return messageDAO.selectByMesssage(messageNum);
        
    }
    
    @Override
    public String checkById(String senderId) throws KdcException {
    
        String checkId = messageDAO.checkById(senderId);
        
        if(senderId.equals(checkId)) {
            return senderId;
        }else {
            return null;
        }
        
    }

}
