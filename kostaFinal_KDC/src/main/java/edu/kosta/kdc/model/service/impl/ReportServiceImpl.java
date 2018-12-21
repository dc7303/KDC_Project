package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.exception.KdcException;
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
    public List<ReportDTO> selectAll(String boardName) {
        
        List<ReportDTO> list = reportDAO.selectAll(boardName);
        
        if(list == null) {
            throw new KdcException("신고내역이 존재하지 않습니다.");
        }
        
        return list;
    }

    /**
     * 관리자 - 신고 삭제 메소드
     * */
    @Override
    public int deleteReport(int reportNum) {
        
        int result = 0;
        
        result = reportDAO.deleteReport(reportNum);
        if(result == 0) {
            throw new KdcException("삭제 실패");
        }
        
        return result;
    }

}
