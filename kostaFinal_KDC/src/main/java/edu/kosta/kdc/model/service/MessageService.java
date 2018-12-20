package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;


public interface MessageService {
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> messageAll(String id);

    /**
     * �޼��� ����
     * */
    int messageInsert(MessageDTO messageDTO);

    /**
     * �޼��� ����
     * */
    int messageDelete(int messageNum);
    
    /**
     * �޼��� �󼼺���(�޼��� Ȯ�� ���� ����)
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * ����ID(serderId) ��ȿ�� üũ
     * */
    String checkById(String senderId);
    
    /**
     * ���� ���� �޼��� ī��Ʈ
     * */
    int unReadCount(String id);

}
