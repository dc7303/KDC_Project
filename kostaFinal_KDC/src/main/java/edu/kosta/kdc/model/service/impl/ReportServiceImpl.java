package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.ReportDAO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ReportService;
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;
    
    /**
     * ������ - �ش� �Խ����� ��� �Ű� �������� �޼ҵ�
     * */
    @Override
    public List<ReportDTO> selectAll(String boardName) {
        
        return reportDAO.selectAll(boardName);
    }

    /**
     * ������ - �Ű� ���� �޼ҵ�
     * */
    @Override
    public int deleteReport(int reportNum) {
        
        return reportDAO.deleteReport(reportNum);
    }

}
