package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int insertReply(ReplyBoardDTO replyBoardDTO) {
        return session.insert("replyBoardMapper.replyBoardInsert", replyBoardDTO);
    }

    @Override
    public int insertHashTag(String hashTagName) {
        return session.insert("replyBoardMapper.hashTagInsert",hashTagName);
    }    
    
    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        return session.insert("replyBoardMapper.replyInsert", replyBoardDTO);
    }

    @Override
    public int readnumUpdate(int replyBoardPk) {
        return session.update("replyBoardMapper.readnumUpdate", replyBoardPk);
    }

    @Override
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB) {
        return session.selectList("replyBoardMapper.boardByModelNum", replyBoardDTODB);
    }

    @Override
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO) {
        return session.update("replyBoardMapper.replyBoardUpdate",replyBoardDTO);
    }

    @Override
    public int hashTagUpdateDelete(ReplyBoardDTO replyBoardDTO) {
        return session.update("replyBoardMapper.hashTagUpdateDelete", replyBoardDTO);
    }

    @Override
    public int hashTagUpdateInsert(ReplyBoardDTO replyBoardDTO, String hashTagName) {
        Map<String, Object> map = new HashMap<>();
        map.put("replyBoardPk", replyBoardDTO.getReplyBoardPk());
        map.put("hashTags", hashTagName);
        
        return session.insert("replyBoardMapper.hashTagUpdateInsert", map);
    }

    @Override
    public int replyBoardDelete(String replyBoardPk) {
        return session.update("replyBoardMapper.replyBoardDelete", replyBoardPk);
    }

    @Override
    public int replyBoardReplyDelete(String replyBoardPk) {
        return session.update("replyBoardMapper.replyBoardReplyDelete", replyBoardPk);
    }

    @Override
    public int replyBoardHashTagDelete(String replyBoardPk) {
        return session.update("replyBoardMapper.replyBoardHashTagDelete", replyBoardPk);
    }

    @Override
    public int replyBoardUpDownDelete(String replyBoardPk) {
        return session.update("replyBoardMapper.replyBoardUpDownDelete", replyBoardPk);
    }

    @Override
    public int replyDelete(int replyBoardReplyPk) {
        return session.update("replyBoardMapper.replyDelete",replyBoardReplyPk);
    }

}
