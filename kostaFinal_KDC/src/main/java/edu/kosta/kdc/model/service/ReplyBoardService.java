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
    int insertReply(ReplyBoardDTO replyBoardDTO,String hashTagName);
    
    int replyInsert(ReplyBoardDTO replyBoardDTO);

    
    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     * @param: state true�̸� ��ȸ������, false�̸� ��ȸ��������.
     *                transaction���� �������Ѵ�. 
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state);

    /**
     * �Խñ� �����ϱ�
     * */
    int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String hashTagName);

    /**
     * �Խñ� �����ϱ�
     * */
    int replyBoardDelete(String replyBoardPk);

}
