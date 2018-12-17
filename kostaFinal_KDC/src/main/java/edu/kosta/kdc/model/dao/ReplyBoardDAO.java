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
    /**
     * 게시글 눌럿을때 조회수 올리기
     * */
    int readnumUpdate(int replyBoardPk);

    /**
     * 게시글 제목에 해당하는 상세보기
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB);
}
