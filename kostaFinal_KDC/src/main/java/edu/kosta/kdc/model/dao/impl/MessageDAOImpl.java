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
     * 전체 메세지 리스트
     */
    @Override
    public List<MessageDTO> messageAll(String id) {

        List<MessageDTO> list = session.selectList("messageMapper.selectAll", id);

        return list;

    }

    /**
     * 메세지 전송
     */
    @Override
    public int messageInsert(MessageDTO messageDTO) {

        return session.insert("messageMapper.messageInsert", messageDTO);

    }

    /**
     * 메세지 번호에 해당하는 메세지 삭제
     */
    @Override
    public int messageDelete(int messageNum) {

            return session.update("messageMapper.messageDelete", messageNum);        

    }

    /**
     * 메세지 상세보기
     */
    @Override
    public MessageDTO selectByMesssage(int messageNum) {

        return session.selectOne("messageMapper.selectByMessage", messageNum);

    }

    /**
     * 메세지 확인 유무
     */
    @Override
    public void messageIsRead(int messageNum) {

        session.update("messageMapper.isReadMessage", messageNum);

    }

    /**
     * 답장ID(serderId) 체크 : 답장버튼을 클릭하면 senderId가 유효한지 체크
     */
    @Override
    public String messageCheckById(String senderId) {

        String checkId = session.selectOne("messageMapper.checkById", senderId);

        return checkId;

    }

    /**
     * 읽지 않은 메세지 카운트
     */
    @Override
    public int messageUnReadCount(String id) {

        int count = session.selectOne("messageMapper.unReadCount", id);

        return count;

    }
    
}
