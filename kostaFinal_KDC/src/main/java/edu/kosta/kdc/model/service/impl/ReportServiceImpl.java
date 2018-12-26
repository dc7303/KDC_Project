package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.ReportDAO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ReportService;
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;
    
    /**
     * 관리자 - 해당 게시판의 모든 신고를 가져오는 메소드
     * */
    @Override
    public List<ReportDTO> selectAllReport(String boardName) {
        
        return reportDAO.selectAllReport(boardName);
    }

    /**
     * 관리자 - 신고 삭제 메소드
     * */
    @Override
    @Transactional
    public List<ReportDTO> deleteReport(int reportNum, String boardName) {
        
        int result = reportDAO.deleteReport(reportNum);
        
        return reportDAO.selectAllReport(boardName);
        
    }

}
