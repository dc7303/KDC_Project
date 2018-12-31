package edu.kosta.kdc.model.dao;

import java.util.List;


import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportDAO {

    /**
     * ����¡ ó���� count ��ȸ
     * 
     * @return
     */
    int reportSelectQuantity();
    
    /**
     * �Ű��� ��ü ��������
     * 
     * @return
     */
    List<ReportDTO> reportSelectAll(int firstColumnRange, int lastColumnRange);
    
    /**
     * ������ - �ش� �Խ����� ��� �Ű���� �������� �޼ҵ�.
     * 
     * @param String
     * @return List<ReportDTO>
     * */
    List<ReportDTO> reportSelectByBoardName(String boardName);

    /**
     * ������ - �Ű� ����
     * 
     * @param int
     * @return int
     * */
    int deleteReport(int reportNum);
    
    /**
     * �Ű� ���� �ڼ��� ����
     * 
     * @param reportPk
     * @return ReportDTO
     */
    ReportDTO selectByReportPk(int reportPk);

}
