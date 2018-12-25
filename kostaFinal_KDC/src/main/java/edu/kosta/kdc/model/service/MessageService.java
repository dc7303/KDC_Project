package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;


public interface MessageService {
    
    /**
     * 전체 메세지 리스트
     * */
    List<MessageDTO> messageAll(String id);

    /**
     * 메세지 전송
     * */
    int messageInsert(MessageDTO messageDTO);

    /**
     * 메세지 삭제
     * */
    int messageDelete(int messageNum);
    
    /**
     * 메세지 상세보기(메세지 확인 유무 포함)
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * 답장ID(serderId) 유효성 체크
     * */
    String checkById(String senderId);
    
    /**
     * 읽지 않은 메세지 카운트
     * */
    int unReadCount(String id);

}
