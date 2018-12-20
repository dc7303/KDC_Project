package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ReportDAO;
import edu.kosta.kdc.model.dto.ReportDTO;
@Repository
public class ReportDAOImpl implements ReportDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 관리자 - 해당 게시판의 모든 신고들을 가져오는 메소드.
     * */
    @Override
    public List<ReportDTO> selectAll(String boardName) {
        
        return sqlSession.selectList("reportMapper.selectReportByBoardName", boardName);
    }

    /**
     * 관리자 - 신고 내용 삭제
     * */
    @Override
    public int deleteReport(int reportNum) {

        return sqlSession.update("reportMapper.deleteReport", reportNum);
    }

}
