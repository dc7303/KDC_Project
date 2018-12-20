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
     * ������ - �ش� �Խ����� ��� �Ű���� �������� �޼ҵ�.
     * */
    @Override
    public List<ReportDTO> selectAll(String boardName) {
        
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
