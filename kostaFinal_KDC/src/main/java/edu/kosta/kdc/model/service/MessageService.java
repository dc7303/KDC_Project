package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.util.KdcException;


public interface MessageService {
    
    /**
     * 전체 메세지 리스트
     * */
    List<MessageDTO> messageAll(String id);

    /**
     * 메세지 전송
     * */
    int messageInsert(MessageDTO messageDTO) throws KdcException;

    /**
     * 메세지 삭제
     * */
    int messageDelete(int messageNum) throws KdcException;
    
    /**
     * 선택된 메세지 삭제
     * */
    int messageSelectDelete(List<Integer> deleteNumList) throws KdcException;
    
    /**
     * 메세지 상세보기(메세지 확인 유무 포함)
     * */
    MessageDTO selectByMesssage(int messageNum);
    
    /**
     * 답장ID(serderId) 체크
     *  : 답장버튼을 클릭하면 senderId가 유효한지 체크
     * */
    String messageCheckById(String senderId);
    
    /**
     * 읽지 않은 메세지 카운트
     * */
    int messageUnReadCount(String id);

}
