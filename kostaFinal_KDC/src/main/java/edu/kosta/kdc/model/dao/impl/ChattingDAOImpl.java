package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ChattingDAO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

@Repository
public class ChattingDAOImpl implements ChattingDAO{

    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public ClassRoomInfoDTO infoSelectByMemberId(String memberId) {
        return sqlSession.selectOne("chattingMapper.infoSelectByMemberId", memberId);
    }

    @Override
    public List<ClassRoomInfoDTO> infoListSelect() {
        List<ClassRoomInfoDTO> result = sqlSession.selectList("chattingMapper.infoListSelect");
        if(result==null) System.out.println("널떳다! 다시해라");
        return result;
    }
    
    
    
}
