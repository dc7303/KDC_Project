package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.util.KdcException;

public interface MessageDAO {

    /**
     * 전체 메세지 리스트
     * */
    List<MessageDTO> messageAll(String id);

    /**
     * 메세지 전송
     * */
    void messageInsert(MessageDTO messageDTO);

    /**
     * 메세지 번호에 해당하는 메세지 삭제
     * */
    int messageDelete(int messageNum);
    
    /**
     * 메세지 상세보기
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * 메세지 확인 유무
     * */
    void isReadMessage(int messageNum);
    
    /**
     * 답장ID(serderId) 유효성 체크
     * */
    String checkById(String senderId);
    
    /**
     * 읽지 않은 메세지 카운트
     * */
    int unReadCount(String id);
    
}
