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
     * ������ ���������� �޽��� Ȯ�� �� �� ��� �޽��� �������� �޼ҵ�
     * */
    @Override
    public List<MessageDTO> messageSelectAll() {
        
        return sqlSession.selectList("messageMapper.adminMessageSelectAll");
        
    }

    /**
     * ������ ���������� ���� ������
     * */
    @Override
    public int insertMessage(MessageDTO messageDTO) {
        
        return sqlSession.insert("messageMapper.insertMessage", messageDTO);
        
    }

    /**
     * ������ ���������� ���� ����
     * */
    @Override
    public int deleteMessage(int messageNum) {
        
        return sqlSession.update("messageMapper.deleteMessage", messageNum);
        
    }

}
