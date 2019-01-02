package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;



public interface MessageDAO {

    /**
     * ��ü �޼��� ����Ʈ(no Paging)
     * */
    List<MessageDTO> messageLIstAllNoPaging();
    
    /**
     * ��ȸ�� �޼��� ����Ʈ �� ��������
     * @return
     */
    int messageSelectQuntity(String memberId);
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> messageAll(String id, int firstColumnRange, int lastColumnRange);
    
    /**
     * �������� ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> unReadMessageList(String id);

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
