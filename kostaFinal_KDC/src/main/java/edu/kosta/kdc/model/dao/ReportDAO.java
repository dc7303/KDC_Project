package edu.kosta.kdc.model.dao;

import java.util.List;


import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportDAO {

    /**
     * �Ű��� ��ü ��������
     * 
     * @return
     */
    List<ReportDTO> reportSelectAll();
    
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

}
