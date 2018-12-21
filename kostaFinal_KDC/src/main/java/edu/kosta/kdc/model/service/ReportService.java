package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.dto.ReportDTO;

public interface ReportService {

    /**
     * ������ - �ش� �Խ����� ��� �Ű���� �������� �޼ҵ�.
     * 
     * @param String
     * @return List<ReportDTO>
     * */
    List<ReportDTO> selectAll(String boardName);

    /**
     * ������ - �Ű� ����
     * 
     * @param int
     * @return int
     * */
    int deleteReport(int reportNum);

}
