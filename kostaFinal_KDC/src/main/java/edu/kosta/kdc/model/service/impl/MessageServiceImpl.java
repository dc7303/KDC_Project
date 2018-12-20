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
     * ������ ���������� �޽��� Ȯ�� �� �� ��� �޽��� �������� �޼ҵ�
     * */
    @Override
    public List<MessageDTO> messageSelectAll() {
        
        return messageDAO.messageSelectAll();
        
    }
    
    /**
     * ������ ���������� ���� ������
     * */
    @Override
    public int insertMessage(MessageDTO messageDTO) {
        
        return messageDAO.insertMessage(messageDTO);
    }

    /**
     * ������ ���������� ���� ����
     * */
    @Override
    public int deleteMessage(int messageNum) {
        
        return messageDAO.deleteMessage(messageNum);
    
    }

}
