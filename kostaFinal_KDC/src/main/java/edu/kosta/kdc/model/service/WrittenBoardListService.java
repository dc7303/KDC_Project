package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface WrittenBoardListService {

    /**
     * ��ü �Խñ� ����Ʈ
     * */
    List<ReplyBoardDTO> writtenBoardList(String id);
    
    /**
     * �Խñ� ����
     * */
    void delelteWrittenBoard(int replyBoardPk);
    
}
