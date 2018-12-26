package edu.kosta.kdc.model.service;

import java.util.List;


import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportService {

    /**
     * ������ - �ش� �Խ����� ��� �Ű���� �������� �޼ҵ�.
     * 
     * @param String
     * @return List<ReportDTO>
     * */
    List<ReportDTO> selectAllReport(String boardName);

    /**
     * ������ - �Ű� ����
     * @param boardName 
     * 
     * @param int, String
     * @return int
     * */
    List<ReportDTO> deleteReport(int reportNum, String boardName);

}
