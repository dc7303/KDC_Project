package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardService {
    
    /**
     * selectAll(전체 정렬)
     * */
    List<ReplyBoardDTO> selectAll(String title);
    
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
     * 게시글 삭제하기
     * */
    int replyBoardDelete(String replyBoardPk);

}
