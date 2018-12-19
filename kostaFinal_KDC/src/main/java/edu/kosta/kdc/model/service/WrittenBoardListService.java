package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface WrittenBoardListService {

    /**
     * 전체 게시글 리스트
     * */
    List<ReplyBoardDTO> writtenBoardList(String id);
    
    /**
     * 게시글 삭제
     * */
    void delelteWrittenBoard(int replyBoardPk);
    
}
