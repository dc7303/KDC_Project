package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.AdminDAO;
import edu.kosta.kdc.model.dto.VisitDTO;
@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 방문자 수 저장하는 메소드
     * */
    @Override
    public int userCountIntoDB(int todayUserCount) {
        
        return sqlSession.insert("adminMapper.insertTodayUserCount", todayUserCount);
    }

    /**
     * 방문자 수 가져오는 메소드 (최근 5일) 
     * */
    @Override
    public List<VisitDTO> visitNumListSelect() {
        
        return sqlSession.selectList("adminMapper.visitNumListSelect");
    }

}
