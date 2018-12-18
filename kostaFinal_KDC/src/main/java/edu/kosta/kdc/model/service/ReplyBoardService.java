package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardService {
    
    /**
     * selectAll(��ü ����)
     * */
    List<ReplyBoardDTO> selectAll(String title);

    /**
     * �����Ͽ� select
     * */
    List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort);

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
     * ��� �����ϱ�
     * */
    int replyUpdate(ReplyBoardDTO replyBoardDTO);
    
    /**
     * �Խñ� �����ϱ�
     * */
    int replyBoardDelete(String replyBoardPk);
    
    /**
     * ��� ���� �ϱ�
     * */
    int replyDelete(int replyBoardReplyPk);


    

}
