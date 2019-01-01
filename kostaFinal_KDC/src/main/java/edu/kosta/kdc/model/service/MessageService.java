package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.util.KdcException;


public interface MessageService {
    
    /**
     * ��ü �޼��� ����Ʈ(no Paging)
     * */
    List<MessageDTO> messageLIstAllNoPaging();
    
    /**
     * ��ȸ�� �޼��� ����Ʈ �� ��������
     * @return
     */
    int messageSelectQuntity();
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> messageAll(int firstColumnRange, int lastColumnRange);
    
    /**
     * �������� ��ü �޼��� ����Ʈ
     * */
    List<MessageDTO> unReadMessageList(String id);

    /**
     * �޼��� ����
     * */
    int messageInsert(MessageDTO messageDTO) throws KdcException;

    /**
     * �޼��� ����
     * */
    int messageDelete(int messageNum) throws KdcException;
    
    /**
     * ���õ� �޼��� ����
     * */
    int messageSelectDelete(List<Integer> deleteNumList) throws KdcException;
    
    /**
     * �޼��� �󼼺���(�޼��� Ȯ�� ���� ����)
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * ����ID(serderId) üũ
     *  : �����ư�� Ŭ���ϸ� senderId�� ��ȿ���� üũ
     * */
    String messageCheckById(String senderId);
    
    /**
     * ���� ���� �޼��� ī��Ʈ
     * */
    int messageUnReadCount();

}
