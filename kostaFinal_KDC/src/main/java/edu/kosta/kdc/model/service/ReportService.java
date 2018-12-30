package edu.kosta.kdc.model.service;

import java.util.List;


import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportService {

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
     * @param boardName 
     * 
     * @param int, String
     * @return int
     * */
    int deleteReport(int reportNum);

}
