package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardDAO {
    
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
     * 1)�Խñ� insert
     * 2)�ؽ��±� insert
     * 3)�Խñ��� ��� insert
     */
    int insertReply(ReplyBoardDTO replyBoardDTO);

    int insertHashTag(String hashTags);
    
    int replyInsert(ReplyBoardDTO replyBoardDTO);
    
    /**
     * �Խñ� �������� ��ȸ�� �ø���
     * */
    int readnumUpdate(int replyBoardPk);

    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB);

    /**
     * �Խñ� �����ϱ�
     * */
    int replyBoardUpdate(ReplyBoardDTO replyBoardDTO);

    /**
     * ��� �����ϱ�
     * */
    int replyUpdate(ReplyBoardDTO replyBoardDTO);
    
   /**
    * �Խñ� ������ �ؽ��±� ����
    * */
    int hashTagUpdateDelete(ReplyBoardDTO replyBoardDTO);
    
    /**
     * �Խñ� ������ �ؽ��±� �ٽ� insert
     * */
    int hashTagUpdateInsert(ReplyBoardDTO replyBoardDTO, String hashTagName);

    /**
     * �Խñ� �����ϱ�
     * */
    int replyBoardDelete(int replyBoardPk);
    
    /**
     * �Խñ��� ��� �����ϱ�
     * */
    int replyBoardReplyDelete(int replyBoardPk);
    
    /**
     * �Խñ��� �ؽ��±� �����ϱ�
     * */
    int replyBoardHashTagDelete(int replyBoardPk);
    
    /**
     * �Խñ��� ���ƿ�� �����ϱ�
     * */
    int replyBoardUpDownDelete(int replyBoardPk);
    
    /**
     * ��� ���� �ϱ�
     * */
    int replyDelete(int replyBoardReplyPk);

    /**
     * replyBoard�Խ��ǿ��� ���Ǻ� �˻�
     * */
    List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch, String classification);

    /**
     * replyBoard���ƿ� ���
     * */
    int replyBoardLike(int replyBoardPk);

    /**
     * replyBoard�Ⱦ�� ���
     * */
    int replyBoardDisLike(int replyBoardPk);
    
    /**
     * replyBoard ���ƿ�, �Ⱦ�� ��� ���
     * */
    int replyBoardLikeCancle(int replyBoardPk);

}
