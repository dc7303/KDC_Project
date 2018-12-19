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
    int replyBoardDelete(int replyBoardPk);
    
    /**
     * ��� ���� �ϱ�
     * */
    int replyDelete(int replyBoardReplyPk);

    /**
     * replyBoard�Խ��ǿ��� ���Ǻ� �˻��ϱ�
     * */
    List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification);

    /**
     * replyBoard ���ƿ�
     * */
    int replyBoardLike(int replyBoardPk);

    /**
     * replyBoard �Ⱦ��
     * */
    int replyBoardDisLike(int replyBoardPk);

    /**
     * replyBoard ���ƿ�,�Ⱦ�� ��� ���
     * */
    int replyBoardLikeCancle(int replyBoardPk);



}
