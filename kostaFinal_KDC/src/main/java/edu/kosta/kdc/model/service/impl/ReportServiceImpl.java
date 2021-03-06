package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ReportDAO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ReportService;
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;
    
    /**
     * 신고 리스트 조회할 수량조회
     */
    @Override
    public int reportSelectQuantity() {
        
        return reportDAO.reportSelectQuantity();
    }
    
    /**
     * 신고 리스트 전체 가져오기
     */
    @Override
    public List<ReportDTO> reportSelectAll(int firstColumnRange, int lastColumnRange) {

        List<ReportDTO> list = reportDAO.reportSelectAll(firstColumnRange, lastColumnRange);
        if(list == null) {
            throw new KdcException("신고 내역 조회 실패입니다.");
        }
        
        return list;
    }
    
    /**
     * 관리자 - 해당 게시판의 모든 신고를 가져오는 메소드
     * */
    @Override
    public List<ReportDTO> reportSelectByBoardName(String boardName) {
        
        List<ReportDTO> list = reportDAO.reportSelectByBoardName(boardName);
        
        if(list == null) {
            throw new KdcException("신고내역이 존재하지 않습니다.");
        }
        return list;
    }

    /**
     * 관리자 - 신고 삭제 메소드
     * */
    @Override
    @Transactional
    public int deleteReport(int reportNum) {
        
        int result = 0;
        
        result = reportDAO.deleteReport(reportNum);
        
        if(result == 0) {
            throw new KdcException("삭제 실패");
        }
        
        return result;
    }

    /**
     * 관리자 - 신고내용 자세히 보기
     */
    @Override
    public ReportDTO selectByReportPk(int reportPk) {
        
        ReportDTO reportDTO = null;
        
        reportDTO = reportDAO.selectByReportPk(reportPk);
        if(reportDTO == null) {
            throw new KdcException("조회하시려는 신고 번호가 잘못되었습니다.");
        }
        
        return reportDTO;
    }

}
