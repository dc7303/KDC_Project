package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;

@Repository
public class ClassRoomDAOImpl implements ClassRoomDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 현재 디폴트로 설정된 클래스룸 가져오기
     */
    @Override
    public ClassRoomDTO currentClassSelectByMemberId(String memberId) {
        
        return sqlSession.selectOne("classRoomMapper.selectCurrentCode", memberId);
    }

}
