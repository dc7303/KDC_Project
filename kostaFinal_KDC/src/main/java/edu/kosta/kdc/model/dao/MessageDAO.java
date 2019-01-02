package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.MessageDTO;



public interface MessageDAO {

    /**
     * 전체 메세지 리스트(no Paging)
     * */
    List<MessageDTO> messageLIstAllNoPaging();
    
    /**
     * 조회할 메세지 리스트 수 가져오기
     * @return
     */
    int messageSelectQuntity(String memberId);
    
    /**
     * 전체 메세지 리스트
     * */
    List<MessageDTO> messageAll(String id, int firstColumnRange, int lastColumnRange);
    
    /**
     * 읽지않은 전체 메세지 리스트
     * */
    List<MessageDTO> unReadMessageList(String id);

    /**
     * 메세지 전송
     * */
    int messageInsert(MessageDTO messageDTO);

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
    void messageIsRead(int messageNum);
    
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
