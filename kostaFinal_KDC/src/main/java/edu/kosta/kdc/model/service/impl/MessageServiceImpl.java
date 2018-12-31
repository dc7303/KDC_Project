package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.util.KdcException;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    /**
     * ��ȸ�� �޼��� ����Ʈ �� ��������
     * @return
     */
    @Override
    public int messageSelectQuntity() {
        
        return messageDAO.messageSelectQuntity();
    }
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    @Override
    public List<MessageDTO> messageAll(int firstColumnRange, int lastColumnRange) {

        MemberDTO memberDTO = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<MessageDTO> messageList = messageDAO.messageAll(memberDTO.getMemberId(), firstColumnRange, lastColumnRange);
        if(messageList == null) {
            throw new KdcException("������ �������� �ʽ��ϴ�.");
        }
        
        return messageList;

    }
    
    /**
     * �������� ��ü �޼��� ����Ʈ
     * */
    @Override
    public List<MessageDTO> unReadMessageList(String id) throws KdcException {
        
        if(! id.equals("")) {
            
            List<MessageDTO> list = messageDAO.unReadMessageList(id);
            
            return list;
            
        }else {
            
            throw new KdcException("ID�� Ȯ�ε��� �ʽ��ϴ�.");
        
        }
        
    }

    /**
     * �޼��� ����
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
     * �޼��� ����
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
     * ���õ� �޼��� ����
     *  : �ϳ��� �޼����� �������� ������ ��ü �ѹ�
     * */
    @Override
    @Transactional
    public int messageSelectDelete(List<Integer> deleteNumList) throws KdcException {
        
        int result = 0;
        
        for(int messageNum : deleteNumList) {
            
            result = messageDAO.messageDelete(messageNum);
            
            if(result == 0) {
                throw new KdcException("������ �����߽��ϴ�.");
            }
            
        }
        
        return result;
        
    }

    /**
     * �޼��� �󼼺���(�޼��� Ȯ�� ���� ����)
     * */
    @Override
    @Transactional
    public MessageDTO selectByMesssage(int messageNum) {

        messageDAO.messageIsRead(messageNum);

        return messageDAO.selectByMesssage(messageNum);

    }

    /**
     * ����ID(serderId) üũ
     *  : �����ư�� Ŭ���ϸ� senderId�� ��ȿ���� üũ
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
     * ���� ���� �޼��� ī��Ʈ
     * */
    @Override
    public int messageUnReadCount() {
        
        MemberDTO memberDTO = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int count = messageDAO.messageUnReadCount(memberDTO.getMemberId());
        
        return count;
    }

}
