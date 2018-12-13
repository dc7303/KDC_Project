package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardDAO {
    
    /**
     * selectAll(��ü ����)
     * */
    List<ReplyBoardDTO> selectAll(String title);

    /**
     * ���ڵ� ����
     */
    int insert(ReplyBoardDTO replyBoardDTO);

    /**
     * �Խñ� �������� ��ȸ�� �ø���
     * */
    int readnumUpdate(String replyBoardTitle);

    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     * */
    ReplyBoardDTO selectByReplyBoardTitle(String replyBoardTitle);
}
