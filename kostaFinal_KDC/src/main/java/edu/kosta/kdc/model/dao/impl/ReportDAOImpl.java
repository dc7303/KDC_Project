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
     * ��ȸ�� �÷� �� ��������
     */
    @Override
    public int reportSelectQuantity() {
        
        return sqlSession.selectOne("reportMapper.reportTotalCount");
    }
    
    /**
     * �Ű� ����Ʈ ��ü ��������
     */
    @Override
    public List<ReportDTO> reportSelectAll(int firstColumnRange, int lastColumnRange) {

        Map<String, Integer> map = new HashMap<>();
        
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        return sqlSession.selectList("reportMapper.selectAll", map);
    }
    
    /**
     * ������ - �ش� �Խ����� ��� �Ű���� �������� �޼ҵ�.
     * */
    @Override
    public List<ReportDTO> reportSelectByBoardName(String boardName) {
        
        return sqlSession.selectList("reportMapper.selectReportByBoardName", boardName);
    }

    /**
     * ������ - �Ű� ���� ����
     * */
    @Override
    public int deleteReport(int reportNum) {

        return sqlSession.update("reportMapper.deleteReport", reportNum);
    }

}
