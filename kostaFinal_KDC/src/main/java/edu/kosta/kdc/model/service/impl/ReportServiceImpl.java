package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ReportDTO> selectAllReport(String boardName) {
        
        return reportDAO.selectAllReport(boardName);
    }

    /**
     * ������ - �Ű� ���� �޼ҵ�
     * */
    @Override
    @Transactional
    public List<ReportDTO> deleteReport(int reportNum, String boardName) {
        
        int result = reportDAO.deleteReport(reportNum);
        
        return reportDAO.selectAllReport(boardName);
        
    }

}
