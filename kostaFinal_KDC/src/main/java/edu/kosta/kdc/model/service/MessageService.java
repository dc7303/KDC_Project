package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;

public interface MessageService {

    /**
     * ������ ������ - ��� ���� ��������
     * 
     * @param
     * @return
     * */
    List<MessageDTO> messageSelectAll();
    
    /**
     * ������ ������ - ���� ����
     * */
    int insertMessage(MessageDTO messageDTO);

    /**
     * ������ ������ - ���� ����
     * */
    int deleteMessage(int messageNum);
}
