package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;



public interface MessageDAO {

    /**
     * ��ȸ�� �޼��� ����Ʈ �� ��������
     * @return
     */
    int messageSelectQuntity();
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> messageAll(String id, int firstColumnRange, int lastColumnRange);

    /**
     * �޼��� ����
     * */
    int messageInsert(MessageDTO messageDTO);

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
    void messageIsRead(int messageNum);
    
    /**
     * ����ID(serderId) üũ
     *  : �����ư�� Ŭ���ϸ� senderId�� ��ȿ���� üũ
     * */
    String messageCheckById(String senderId);
    
    /**
     * ���� ���� �޼��� ī��Ʈ
     * */
    int messageUnReadCount(String id);
    
}
