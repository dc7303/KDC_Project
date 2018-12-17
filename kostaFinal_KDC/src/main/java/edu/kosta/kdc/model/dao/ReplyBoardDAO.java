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
    int replyBoardDelete(String replyBoardPk);
    
    /**
     * �Խñ��� ��� �����ϱ�
     * */
    int replyBoardReplyDelete(String replyBoardPk);
    
    /**
     * �Խñ��� �ؽ��±� �����ϱ�
     * */
    int replyBoardHashTagDelete(String replyBoardPk);
    
    /**
     * �Խñ��� ���ƿ�� �����ϱ�
     * */
    int replyBoardUpDownDelete(String replyBoardPk);
}
