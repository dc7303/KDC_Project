package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;

public interface MessageService {

    /**
     * 관리자 페이지 - 모든 쪽지 가져오기
     * 
     * @param
     * @return
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
