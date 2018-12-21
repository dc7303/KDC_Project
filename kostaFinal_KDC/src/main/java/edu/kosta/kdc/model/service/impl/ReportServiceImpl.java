package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.exception.KdcException;
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
        
        List<ReportDTO> list = reportDAO.selectAll(boardName);
        
        if(list == null) {
            throw new KdcException("�Ű����� �������� �ʽ��ϴ�.");
        }
        
        return list;
    }

    /**
     * ������ - �Ű� ���� �޼ҵ�
     * */
    @Override
    public int deleteReport(int reportNum) {
        
        int result = 0;
        
        result = reportDAO.deleteReport(reportNum);
        if(result == 0) {
            throw new KdcException("���� ����");
        }
        
        return result;
    }

}
