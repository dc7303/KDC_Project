package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardDAO {
    
    /**
     * selectAll(전체 정렬)
     * */
    List<ReplyBoardDTO> selectAll(String title);

    /**
     * 레코드 삽입
     */
    int insertReply(ReplyBoardDTO replyBoardDTO);

    int insertHashTag(String hashTags);
    
    int replyInsert(ReplyBoardDTO replyBoardDTO);
    
    /**
     * 게시글 눌럿을때 조회수 올리기
     * */
    int readnumUpdate(int replyBoardPk);

    /**
     * 게시글 제목에 해당하는 상세보기
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB);

    /**
     * 게시글 수정하기
     * */
    int replyBoardUpdate(ReplyBoardDTO replyBoardDTO);

   /**
    * 게시글 수정시 해시태그 삭제
    * */
    int hashTagUpdateDelete(ReplyBoardDTO replyBoardDTO);
    
    /**
     * 게시글 수정후 해시태그 다시 insert
     * */
    int hashTagUpdateInsert(ReplyBoardDTO replyBoardDTO, String hashTagName);

    /**
     * 게시글 삭제하기
     * */
    int replyBoardDelete(String replyBoardPk);
    
    /**
     * 게시글의 댓글 삭제하기
     * */
    int replyBoardReplyDelete(String replyBoardPk);
    
    /**
     * 게시글의 해시태그 삭제하기
     * */
    int replyBoardHashTagDelete(String replyBoardPk);
    
    /**
     * 게시글의 좋아요수 삭제하기
     * */
    int replyBoardUpDownDelete(String replyBoardPk);
}
