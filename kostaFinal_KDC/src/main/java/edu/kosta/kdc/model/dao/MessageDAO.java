package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;

public interface MessageDAO {

    /**
     * 관리자 페이지에서 메시지 확인 할 때 모든 메시지 가져오는 메소드
     * */
    List<MessageDTO> messageSelectAll();

    /**
     * 관리자 페이지 - 쪽지 쓰기
     * */
    int insertMessage(MessageDTO messageDTO);

    /**
     * 관리자 페이지 - 쪽지 삭제
     * */
    int deleteMessage(int messageNum);

}
