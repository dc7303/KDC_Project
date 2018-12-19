package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardService {
    
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
     */
    int insertReply(ReplyBoardDTO replyBoardDTO,String hashTagName);
    
    int replyInsert(ReplyBoardDTO replyBoardDTO);

    
    /**
     * 게시글 제목에 해당하는 상세보기
     * @param: state true이면 조회수증가, false이면 조회증가안함.
     *                transaction으로 묶여야한다. 
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state);

    /**
     * 게시글 수정하기
     * */
    int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String hashTagName);

    /**
     * 댓글 수정하기
     * */
    int replyUpdate(ReplyBoardDTO replyBoardDTO);
    
    /**
     * 게시글 삭제하기
     * */
    int replyBoardDelete(int replyBoardPk);
    
    /**
     * 댓글 삭제 하기
     * */
    int replyDelete(int replyBoardReplyPk);

    /**
     * replyBoard게시판에서 조건별 검색하기
     * */
    List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification);

    /**
     * replyBoard 좋아요
     * */
    int replyBoardLike(int replyBoardPk);

    /**
     * replyBoard 싫어요
     * */
    int replyBoardDisLike(int replyBoardPk);

    /**
     * replyBoard 좋아요,싫어요 취소 기능
     * */
    int replyBoardLikeCancle(int replyBoardPk);



}
