package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.util.KdcException;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    /**
     * 전체 메세지 리스트
     * */
    @Override
    public List<MessageDTO> messageAll(String id) throws edu.kosta.kdc.exception.KdcException{

        List<MessageDTO> list = messageDAO.messageAll(id);
        
        return list;

    }
    
    /**
     * 읽지않은 전체 메세지 리스트
     * */
    @Override
    public List<MessageDTO> unReadMessageList(String id) throws KdcException {
        
        if(! id.equals("")) {
            
            List<MessageDTO> list = messageDAO.unReadMessageList(id);
            
            return list;
            
        }else {
            
            throw new KdcException("ID가 확인되지 않습니다.");
        
        }
        
    }

    /**
     * 메세지 전송
     * */
    @Override
    public int messageInsert(MessageDTO messageDTO) throws KdcException {

        int result = 0;
        
        result = messageDAO.messageInsert(messageDTO);
        
        if (result == 0)
            throw new KdcException();

        return result;
    }

    /**
     * 메세지 삭제
     * */
    @Override
    public int messageDelete(int messageNum) throws KdcException {

        int result = 0;
        
        result = messageDAO.messageDelete(messageNum);
        
        if (result == 0)
            throw new KdcException();

        return result;
        
    }
    
    /**
     * 선택된 메세지 삭제
     *  : 하나의 메세지라도 삭제되지 않으면 전체 롤백
     * */
    @Override
    @Transactional
    public int messageSelectDelete(List<Integer> deleteNumList) throws KdcException {
        
        int result = 0;
        
        for(int messageNum : deleteNumList) {
            
            result = messageDAO.messageDelete(messageNum);
            
            if(result == 0) {
                throw new KdcException("삭제에 실패했습니다.");
            }
            
        }
        
        return result;
        
    }

    /**
     * 메세지 상세보기(메세지 확인 유무 포함)
     * */
    @Override
    @Transactional
    public MessageDTO selectByMesssage(int messageNum) {

        messageDAO.messageIsRead(messageNum);

        return messageDAO.selectByMesssage(messageNum);

    }

    /**
     * 답장ID(serderId) 체크
     *  : 답장버튼을 클릭하면 senderId가 유효한지 체크
     * */
    @Override
    public String messageCheckById(String senderId) throws KdcException {

        String checkId = messageDAO.messageCheckById(senderId);

        if (senderId.equals(checkId)) {
            return senderId;
        } else {
            return null;
        }

    }

    /**
     * 읽지 않은 메세지 카운트
     * */
    @Override
    public int messageUnReadCount(String id) {

        int count = messageDAO.messageUnReadCount(id);

        return count;
    }

}
