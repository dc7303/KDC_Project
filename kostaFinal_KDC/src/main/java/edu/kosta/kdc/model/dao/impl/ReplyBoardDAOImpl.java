package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;

@Repository
public class ReplyBoardDAOImpl implements ReplyBoardDAO {

    @Autowired
    private SqlSession session;
    
    @Override
    public List<ReplyBoardDTO> selectAll(String title) {
        return session.selectList("replyBoardMapper.replyBoardSelectAll", title);
    }
    
    @Override
    public int insert(ReplyBoardDTO replyBoardDTO) {
        return session.insert("replyBoardMapper.replyBoardInsert", replyBoardDTO);
    }

    @Override
    public int readnumUpdate(int replyBoardPk) {
        return session.update("replyBoardMapper.readnumUpdate", replyBoardPk);
    }

    @Override
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB) {
        List<ReplyBoardDTO> list = session.selectList("replyBoardMapper.boardByModelNum", replyBoardDTODB);
        for(ReplyBoardDTO dto:list) {
            System.out.println(dto.getUpdown());
        }
        return list;
    }

}
