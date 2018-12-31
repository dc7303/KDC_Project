package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.WrittenBoardListDAO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;

@Repository
public class WrittenBoardListDAOImpl implements WrittenBoardListDAO {
    
    @Autowired
    private SqlSession session;
    
    @Override
    public List<ReplyBoardDTO> writtenBoardList(String id) {
        
        List<ReplyBoardDTO> list = session.selectList("replyBoardMapper.writtenBoardList", id);
        
        return list;
    }
    
    @Override
    public void delelteWrittenBoard(int replyBoardPk) {
        
        session.update("replyBoardMapper.replyBoardDelete", replyBoardPk);
        
    }

}
