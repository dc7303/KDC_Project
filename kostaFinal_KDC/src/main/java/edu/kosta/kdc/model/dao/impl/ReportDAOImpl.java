package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 조회할 컬럼 수 가져오기
     */
    @Override
    public int reportSelectQuantity() {
        
        return sqlSession.selectOne("reportMapper.reportTotalCount");
    }
    
    /**
     * 신고 리스트 전체 가져오기
     */
    @Override
    public List<ReportDTO> reportSelectAll(int firstColumnRange, int lastColumnRange) {

        Map<String, Integer> map = new HashMap<>();
        
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        return sqlSession.selectList("reportMapper.selectAll", map);
    }
    
    /**
     * 관리자 - 해당 게시판의 모든 신고들을 가져오는 메소드.
     * */
    @Override
    public List<ReportDTO> reportSelectByBoardName(String boardName) {
        
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
