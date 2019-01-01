package edu.kosta.kdc.model.dao;

import java.util.List;
import java.util.Map;

import edu.kosta.kdc.model.dto.HashTagDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardDAO {
    
    /**
     * selectAll(��ü ����)
     * */
    List<ReplyBoardDTO> selectAll(Map<String, Object> map);

    /**
     * �����Ͽ� select
     * */
    List<ReplyBoardDTO> replyBoardSelectAllOrderBy(Map<String, Object> map);
    
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
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTO);

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
    List<ReplyBoardDTO> replyBoardListSearch(Map<String, Object> map);

    /**
     * replyBoard���ƿ� ���
     * */
    int replyBoardLike(ReplyBoardDTO replyBoardDTO);

    /**
     * replyBoard�Ⱦ�� ���
     * */
    int replyBoardDisLike(ReplyBoardDTO replyBoardDTO);
    
    /**
     * replyBoard ���ƿ�, �Ⱦ�� ��� ���
     * */
    int replyBoardLikeCancle(ReplyBoardDTO replyBoardDTO);
    
    /**
     * �ؽ��±� �����ϱ�
     * */
    List<HashTagDTO> hashtagSuggest(String keyword);
    
    /**
     * �Ű��ϱ� insert(radio�ڽ��� �ִ°� üũ�ҽ�)
     * */
    int reportPopInsert(String reportContents, int replyBoardPk, String memberId);
    
    /**
     * �Ű��ϱ� insert(radio�ڽ����� ��Ÿ�� �������� ���)
     * */
    int reportPopOtherInsert(String otherWords, int replyBoardPkReport, String memberId);
    
    /**
     * ����±� �����ϱ�
     * */
    List<MemberDTO> mentionSuggest(String keyword);
    
    /**
     * ��� �г��� ������
     * */
    List<MemberDTO> allNicknames();

    /**
     * classification �������� �÷� ���� ��������.
     *
     * @param classification
     * @return
     */
    public int boardQuantityByClassification(String classification);

    /**
     * ����ȭ�鿡 ��� ����ִ� �Խ��� �Խñ� 5�� ��������
     * @param String 
     * */
    List<ReplyBoardDTO> selectFiveByTitle(String title);

    /**
     * �Խ��� ������ �˻��� ��ü �Խù� �� ��������
     */
    int boardQuantityByClassificationwithSearch(Map<String, Object> map);

}
