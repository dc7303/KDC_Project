package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardDAO {
    
    /**
     * selectAll(전체 정렬)
     * */
    List<ReplyBoardDTO> selectAll(String title);

    /**
     * 정렬하여 select
     * */
    List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort);
    
    /**
     * 레코드 삽입
     * 1)게시글 insert
     * 2)해시태그 insert
     * 3)게시글의 댓글 insert
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
     * 댓글 수정하기
     * */
    int replyUpdate(ReplyBoardDTO replyBoardDTO);
    
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
    int replyBoardDelete(int replyBoardPk);
    
    /**
     * 게시글의 댓글 삭제하기
     * */
    int replyBoardReplyDelete(int replyBoardPk);
    
    /**
     * 게시글의 해시태그 삭제하기
     * */
    int replyBoardHashTagDelete(int replyBoardPk);
    
    /**
     * 게시글의 좋아요수 삭제하기
     * */
    int replyBoardUpDownDelete(int replyBoardPk);
    
    /**
     * 댓글 삭제 하기
     * */
    int replyDelete(int replyBoardReplyPk);

    /**
     * replyBoard게시판에서 조건별 검색
     * */
    List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch, String classification);

    /**
     * replyBoard좋아요 기능
     * */
    int replyBoardLike(int replyBoardPk);

    /**
     * replyBoard싫어요 기능
     * */
    int replyBoardDisLike(int replyBoardPk);
    
    /**
     * replyBoard 좋아요, 싫어요 취소 기능
     * */
    int replyBoardLikeCancle(int replyBoardPk);

}
