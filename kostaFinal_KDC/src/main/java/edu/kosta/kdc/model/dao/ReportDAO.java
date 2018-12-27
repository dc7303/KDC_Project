package edu.kosta.kdc.model.dao;

import java.util.List;


import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportDAO {

    /**
     * 신고내역 전체 가져오기
     * 
     * @return
     */
    List<ReportDTO> reportSelectAll();
    
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

}
