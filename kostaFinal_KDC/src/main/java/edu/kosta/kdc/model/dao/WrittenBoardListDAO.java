package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface WrittenBoardListDAO {
    
    /**
     * ��ü �Խñ� ����Ʈ
     * */
    List<ReplyBoardDTO> writtenBoardList(String id);
    
    /**
     * �Խñ� ����
     * */
    void delelteWrittenBoard(int replyBoardPk);

}
