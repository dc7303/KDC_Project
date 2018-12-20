package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.util.KdcException;

public interface MessageDAO {

    /**
     * ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> messageAll(String id);

    /**
     * �޼��� ����
     * */
    void messageInsert(MessageDTO messageDTO);

    /**
     * �޼��� ��ȣ�� �ش��ϴ� �޼��� ����
     * */
    int messageDelete(int messageNum);
    
    /**
     * �޼��� �󼼺���
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * �޼��� Ȯ�� ����
     * */
    void isReadMessage(int messageNum);
    
    /**
     * ����ID(serderId) ��ȿ�� üũ
     * */
    String checkById(String senderId);
    
    /**
     * ���� ���� �޼��� ī��Ʈ
     * */
    int unReadCount(String id);
    
}
