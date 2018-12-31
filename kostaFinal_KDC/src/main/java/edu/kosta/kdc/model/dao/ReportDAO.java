package edu.kosta.kdc.model.dao;

import java.util.List;


import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportDAO {

    /**
     * 페이징 처리시 count 조회
     * 
     * @return
     */
    int reportSelectQuantity();
    
    /**
     * 신고내역 전체 가져오기
     * 
     * @return
     */
    List<ReportDTO> reportSelectAll(int firstColumnRange, int lastColumnRange);
    
    /**
     * 관리자 - 해당 게시판의 모든 신고들을 가져오는 메소드.
     * 
     * @param String
     * @return List<ReportDTO>
     * */
    List<ReportDTO> reportSelectByBoardName(String boardName);

    /**
     * 관리자 - 신고 삭제
     * 
     * @param int
     * @return int
     * */
    int deleteReport(int reportNum);
    
    /**
     * 신고 내용 자세히 보기
     * 
     * @param reportPk
     * @return ReportDTO
     */
    ReportDTO selectByReportPk(int reportPk);

}
