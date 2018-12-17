package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardService {
    
    /**
     * selectAll(��ü ����)
     * */
    List<ReplyBoardDTO> selectAll(String title);
    
    /**
     * ���ڵ� ����
     */
    int insertReply(ReplyBoardDTO replyBoardDTO);
    
    int insertHashTag(String hashTagName);

    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     * @param: state true�̸� ��ȸ������, false�̸� ��ȸ��������.
     *                transaction���� �������Ѵ�. 
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state);

    
}
