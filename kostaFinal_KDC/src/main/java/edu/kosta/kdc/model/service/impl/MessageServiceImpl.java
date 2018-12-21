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
    public List<MessageDTO> messageAll(String id) {

        return messageDAO.messageAll(id);

    }

    @Override
    public int messageInsert(MessageDTO messageDTO) {

        int result = 0;
        
        result = messageDAO.messageInsert(messageDTO);
        if (result == 0)
            throw new KdcException();

        return result;
    }

    @Override
    public int messageDelete(int messageNum) {

        int result = 0;
        
        result = messageDAO.messageDelete(messageNum);
        if (result == 0)
            throw new KdcException();

        return result;
    }

    @Override
    @Transactional
    public MessageDTO selectByMesssage(int messageNum) {

        return messageDAO.selectByMesssage(messageNum);

    }

    @Override
    public String checkById(String senderId) throws KdcException {

        String checkId = messageDAO.checkById(senderId);

        if (senderId.equals(checkId)) {
            return senderId;
        } else {
            return null;
        }

    }

    @Override
    public int unReadCount(String id) {

        int count = messageDAO.unReadCount(id);

        return count;
    }

}
