package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;

public interface MessageDAO {

    /**
     * ������ ���������� �޽��� Ȯ�� �� �� ��� �޽��� �������� �޼ҵ�
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
