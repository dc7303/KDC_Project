package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.util.KdcException;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SqlSession session;
    
    
    @Override
    public List<MessageDTO> messageAll(String id){
        
        List<MessageDTO> list  = session.selectList("messageMapper.selectAll", id);
        
        return list;
        
    }

    @Override
    public void messageInsert(MessageDTO messageDTO){
        
        session.insert("messageMapper.messageInsert", messageDTO);
        
    }

    @Override
    public int messageDelete(int messageNum){
        
        return session.update("messageMapper.messageDelete", messageNum);
        
    }

    @Override
    public MessageDTO selectByMesssage(int messageNum){
        
        return session.selectOne("messageMapper.selectByMessage", messageNum);
        
    }
    
    @Override
    public void isReadMessage(int messageNum){
        
        session.update("messageMapper.isReadMessage", messageNum);
        
    }
    
    @Override
    public String checkById(String senderId){
        
        String checkId = session.selectOne("messageMapper.checkById", senderId);
        
        return checkId;
        
    }

}
