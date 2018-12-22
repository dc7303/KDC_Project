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
    private SqlSession session;

    /**
     * ��ü �޼��� ����Ʈ
     */
    @Override
    public List<MessageDTO> messageAll(String id) {

        List<MessageDTO> list = session.selectList("messageMapper.selectAll", id);

        return list;

    }

    /**
     * �޼��� ����
     */
    @Override
    public int messageInsert(MessageDTO messageDTO) {

        return session.insert("messageMapper.messageInsert", messageDTO);

    }

    /**
     * �޼��� ��ȣ�� �ش��ϴ� �޼��� ����
     */
    @Override
    public int messageDelete(int messageNum) {

            return session.update("messageMapper.messageDelete", messageNum);        

    }

    /**
     * �޼��� �󼼺���
     */
    @Override
    public MessageDTO selectByMesssage(int messageNum) {

        return session.selectOne("messageMapper.selectByMessage", messageNum);

    }

    /**
     * �޼��� Ȯ�� ����
     */
    @Override
    public void messageIsRead(int messageNum) {

        session.update("messageMapper.isReadMessage", messageNum);

    }

    /**
     * ����ID(serderId) üũ : �����ư�� Ŭ���ϸ� senderId�� ��ȿ���� üũ
     */
    @Override
    public String messageCheckById(String senderId) {

        String checkId = session.selectOne("messageMapper.checkById", senderId);

        return checkId;

    }

    /**
     * ���� ���� �޼��� ī��Ʈ
     */
    @Override
    public int messageUnReadCount(String id) {

        int count = session.selectOne("messageMapper.unReadCount", id);

        return count;

    }
    
}
