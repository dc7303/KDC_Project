package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ReportDAO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ReportService;
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;
    
    /**
     * �Ű� ����Ʈ ��ȸ�� ������ȸ
     */
    @Override
    public int reportSelectQuantity() {
        
        int result = 0;
        
        result = reportDAO.reportSelectQuantity();
        if(result == 0) {
            throw new KdcException("��ȸ�� �Ű����� �����ϴ�.");
        }
        
        return result;
    }
    
    /**
     * �Ű� ����Ʈ ��ü ��������
     */
    @Override
    public List<ReportDTO> reportSelectAll(int firstColumnRange, int lastColumnRange) {

        List<ReportDTO> list = reportDAO.reportSelectAll(firstColumnRange, lastColumnRange);
        if(list == null) {
            throw new KdcException("�Ű� ���� ��ȸ �����Դϴ�.");
        }
        
        return list;
    }
    
    /**
     * ������ - �ش� �Խ����� ��� �Ű� �������� �޼ҵ�
     * */
    @Override
    public List<ReportDTO> reportSelectByBoardName(String boardName) {
        
        List<ReportDTO> list = reportDAO.reportSelectByBoardName(boardName);
        
        if(list == null) {
            throw new KdcException("�Ű����� �������� �ʽ��ϴ�.");
        }
        return list;
    }

    /**
     * ������ - �Ű� ���� �޼ҵ�
     * */
    @Override
    @Transactional
    public List<ReportDTO> deleteReport(int reportNum, String boardName) {
        
        int result = 0;
        
        result = reportDAO.deleteReport(reportNum);
        
        if(result == 0) {
            throw new KdcException("���� ����");
        }
        
        return reportDAO.reportSelectByBoardName(boardName);
    }

}
