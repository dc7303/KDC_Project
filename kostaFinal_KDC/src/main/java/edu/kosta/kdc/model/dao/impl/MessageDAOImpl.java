package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MessageDTO;
@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 관리자 페이지에서 메시지 확인 할 때 모든 메시지 가져오는 메소드
     * */
    @Override
    public List<MessageDTO> messageSelectAll() {
        
        return sqlSession.selectList("messageMapper.adminMessageSelectAll");
        
    }

    /**
     * 관리자 페이지에서 쪽지 보내기
     * */
    @Override
    public int insertMessage(MessageDTO messageDTO) {
        
        return sqlSession.insert("messageMapper.insertMessage", messageDTO);
        
    }

    /**
     * 관리자 페이지에서 쪽지 삭제
     * */
    @Override
    public int deleteMessage(int messageNum) {
        
        return sqlSession.update("messageMapper.deleteMessage", messageNum);
        
    }

}
