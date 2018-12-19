package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.util.KdcException;

public interface MessageService {
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> messageAll(String id);

    /**
     * �޼��� ����
     * */
    void messageInsert(MessageDTO messageDTO);

    /**
     * �޼��� ����
     * */
    void messageDelete(int messageNum);
    
    /**
     * �޼��� �󼼺���(�޼��� Ȯ�� ���� ����)
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * ����ID(serderId) üũ
     * */
    String checkById(String senderId);

}
